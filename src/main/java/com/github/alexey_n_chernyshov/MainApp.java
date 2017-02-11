/**
 * @author Alexey Chernyshov
 */

package com.github.alexey_n_chernyshov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainApp {

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        if (args.length < 1) {
            System.out.println("Please pass in the filename.");
            System.exit(1);
        }

        GoParser parser = new GoParser(new FileInputStream(args[0]));
        SimpleNode root = GoParser.Program();

        System.out.println("Abstract Syntax Tree:");
        root.dump(" ");

        System.out.println();
        System.out.println("Program:");
        PrintVisitor pv = new PrintVisitor();
        root.jjtAccept(pv, null);
    }

}
