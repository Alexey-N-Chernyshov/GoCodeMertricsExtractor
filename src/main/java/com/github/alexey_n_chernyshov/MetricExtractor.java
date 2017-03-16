/**
 * @author Alexey Chernyshov
 */

package com.github.alexey_n_chernyshov;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Extract metrics from projects in the Go lang.
 */
public class MetricExtractor {

    private final String SEPARATOR = "\t";
    /**
     * Map of parsed sources
     */
    HashMap<String, SimpleNode> astSources = new HashMap<>();
    /**
     * Contains all the metrics.
     */
    List<SourceFile> sources = new ArrayList<>();
    /**
     * List of files not parsed.
     */
    List<ProblemFile> problemFiles = new ArrayList<>();
    int totalParsed = 0;

    /**
     * @return number of files not parsed
     */
    int getProblemFilesCount() {
        return problemFiles.size();
    }

    public void parseFile(String filename) {
        try {
            totalParsed++;
            Preprocessor p = new Preprocessor();
            GoParser parser = new GoParser(p.addSemicolons(new FileInputStream(filename)));
            SimpleNode root = parser.getRoot();
            astSources.put(filename, root);

            StatementCounterVisitor statementCounterVisitor = new StatementCounterVisitor();
            root.jjtAccept(statementCounterVisitor, null);

            CyclomaticComplexityVisitor cyclomaticComplexityVisitor = new CyclomaticComplexityVisitor();
            root.jjtAccept(cyclomaticComplexityVisitor, null);

            OOPMeasuresVisitor oopMeasuresVisitor = new OOPMeasuresVisitor();
            root.jjtAccept(oopMeasuresVisitor, null);

            SourceFile sourceFile = new SourceFile(filename, statementCounterVisitor.getStatements());
            for (Map.Entry<String, Integer> entry : cyclomaticComplexityVisitor.getComplexity().entrySet()) {
                sourceFile.addFunction(new Method(entry.getKey(), entry.getValue()));
            }
            for (Map.Entry<String, HashSet<String>> entry : oopMeasuresVisitor.getStructure().entrySet()) {
                sourceFile.addStruct(new Struct(entry.getKey(), entry.getValue().size()));
            }
            sources.add(sourceFile);
        } catch (Exception e) {
            problemFiles.add(new ProblemFile(filename, e.toString()));
        }
    }

    public void parseDir(String path) throws IOException {
        Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .filter(p -> p.getFileName().toString().endsWith(".go"))
                .forEach(p -> parseFile(p.toString()));
    }

    /**
     * Outputs encountered problems in files.
     */
    public void printProblemFiles() {
        System.out.println("Problem files (total: " + problemFiles.size() + "):");
        if (!problemFiles.isEmpty()) {
            problemFiles.forEach((s) -> System.out.println(s));
        }
    }

    /**
     * Prints metrics for files:
     * - statements count
     */
    public void printFilesMetrics() {
        System.out.println("file metrics (total: " + sources.size() + ")");
        System.out.println("filename statementCount");
        sources.forEach((s) -> {
            System.out.println(s.filename + SEPARATOR + s.statementCount);
        });
    }

    /**
     * Prints metrics for structures:
     * - methods attached to structure
     */
    public void printStructMetrics() {
        int totalStruct = 0;
        for (SourceFile s : sources) {
            totalStruct += s.structs.size();
        }
        System.out.println("struct metrics (total: " + totalStruct + ")");
        System.out.println("filename structName methodCount");

        for (SourceFile s : sources) {
            for (Struct struct : s.structs) {
                System.out.println(s.filename + ": " + struct.name + SEPARATOR + struct.methodCount);
            }
        }
    }

    /**
     * Prints metrics for methods:
     * - cyclomatic complexity
     */
    public void printMethodMetrics() {
        int totalMethods = 0;
        for (SourceFile s : sources) {
            totalMethods += s.methods.size();
        }
        System.out.println("method metrics (total: " + totalMethods + ")");
        System.out.println("filename methodName cyclomaticComplexity");

        for (SourceFile s : sources) {
            for (Method method : s.methods) {
                System.out.println(s.filename + ": " + method.name + SEPARATOR + method.cyclomaticComplexity);
            }
        }
    }

    public void printReport() {
        printProblemFiles();
        printFilesMetrics();
        printStructMetrics();
        printMethodMetrics();
    }

    class ProblemFile {

        String filename;
        String problem;

        ProblemFile(String filename, String problem) {
            this.filename = filename;
            this.problem = problem;
        }

        @Override
        public String toString() {
            return filename + ": " + problem;
        }
    }

    /**
     * Metrics for function of the source file.
     */
    class Method {
        String name;
        int cyclomaticComplexity;

        Method(String name, int cyclomaticComplexity) {
            this.name = name;
            this.cyclomaticComplexity = cyclomaticComplexity;
        }
    }

    /**
     * Metrics for struct of the source file.
     */
    class Struct {
        String name;
        int methodCount;

        Struct(String name, int methodCount) {
            this.name = name;
            this.methodCount = methodCount;
        }
    }

    /**
     * Stores all metrics parsed.
     */
    class SourceFile {
        String filename;
        int statementCount;
        HashSet<Method> methods = new HashSet<>();
        HashSet<Struct> structs = new HashSet<>();

        SourceFile(String filename, int statementCount) {
            this.filename = filename;
            this.statementCount = statementCount;
        }

        void addFunction(Method method) {
            methods.add(method);
        }

        void addStruct(Struct struct) {
            structs.add(struct);
        }
    }

}
