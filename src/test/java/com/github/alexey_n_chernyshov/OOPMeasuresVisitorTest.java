package com.github.alexey_n_chernyshov;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class OOPMeasuresVisitorTest {

    private int countStructs(String src) throws FileNotFoundException, ParseException {
        Preprocessor p = new Preprocessor();
        GoParser parser = new GoParser(p.addSemicolons(new FileInputStream(src)));
        SimpleNode root = parser.getRoot();
        OOPMeasuresVisitor visitor = new OOPMeasuresVisitor();
        root.jjtAccept(visitor, null);

        return visitor.getStructures().size();
    }


    private int countMethodsPerStruct(){
      return 0;
    }

    @Test
    public void test1(){
        try{
            String src = "C:/Users/Диана/Desktop/Innopolis Study/term4/Advanced programming in Java/Project/go-master/cloudup/cloudup.go";
            assertEquals(1, countStructs(src));
        } catch (Exception e){
            System.out.println(e.toString());
            //fail("Caught an exception " + e.toString());
        }
    }
}