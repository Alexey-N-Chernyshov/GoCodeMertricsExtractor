/**
 * @author Alexey Chernyshov
 */

package com.github.alexey_n_chernyshov;

import java.io.FileNotFoundException;

public class MainApp {

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        if (args.length < 1) {
            System.out.println("Please pass in the path.");
            System.exit(1);
        }

        try {
            MetricExtractor me = new MetricExtractor();
            me.parseDir(args[0]);
            me.printReport();
            System.out.print(me.totalParsed + "/" + me.getProblemFilesCount());
        } catch (Exception e) {
            System.out.println(e);
        }

//        Preprocessor p = new Preprocessor();
//        GoParser parser = new GoParser(p.addSemicolons(new FileInputStream(args[0])));
//        SimpleNode root = parser.getRoot();
//
//        System.out.println("Abstract Syntax Tree:");
//        root.dump(" ");
//
//        System.out.println();
//        System.out.println("Program:");
//        PrintVisitor pv = new PrintVisitor(System.out);
//        root.jjtAccept(pv, null);
    }

}
