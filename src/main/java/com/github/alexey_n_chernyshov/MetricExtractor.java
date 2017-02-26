/**
 * @author Alexey Chernyshov
 */

package com.github.alexey_n_chernyshov;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Extract metrics from projects in the Go lang.
 */
public class MetricExtractor {

    HashMap<String, SimpleNode> sources = new HashMap<>();

    public void parseFile(String filename) {
        try {
            Preprocessor p = new Preprocessor();
            GoParser parser = new GoParser(p.addSemicolons(new FileInputStream(filename)));
            sources.put(filename, parser.getRoot());
        } catch (Exception e) {
            //TODO hanlde exception (list of unparsed files with exceptions)
            System.out.println(filename);
            System.out.println(e);
        }
    }

    public void parseDir(String path) throws IOException {
        Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .filter(p -> p.getFileName().toString().endsWith(".go"))
                .forEach(p -> parseFile(p.toString()));
    }

    public void printRepot() {
        for (Map.Entry<String, SimpleNode> entry : sources.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

}