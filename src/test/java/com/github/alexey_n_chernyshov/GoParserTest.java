/**
 * @author Alexey Chernyshov
 */

package com.github.alexey_n_chernyshov;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GoParserTest {
    @Before
    public void setUp() throws Exception {

    }

    /**
     * Parses source as a string input.
     *
     * @param src
     * @return parser
     */
    private GoParser createParser(String src) {
        InputStream inStream = new ByteArrayInputStream(src.getBytes(StandardCharsets.UTF_8));
        return new GoParser(inStream);
    }

    /**
     * Traverse AST and builds result string.
     *
     * @return
     */
    private String getParseResult(SimpleNode root) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        PrintVisitor pv = new PrintVisitor(out);
        root.jjtAccept(pv, null);
        return outStream.toString();
    }

    /**
     * Tests decimal integer literals for GoParser.
     */
    @Test
    public void testDecimalIntegerLiterals() {
        String src = "1234567890";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests decimal integer literals for GoParser.
     * It shouldn't start with 0.
     */
    @Test(expected = ParseException.class)
    public void testDecimalIntegerLiterals1() throws ParseException {
        String src = "01234567890";
        GoParser parser = createParser(src);
        parser.Program();
    }

    /**
     * Tests hex integer literals for GoParser.
     */
    @Test
    public void testHexIntegerLiterals() {
        String src = "0xABC1234567890";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests hex integer literals for GoParser.
     */
    @Test
    public void testHexIntegerLiterals1() {
        String src = "0XABC1234567890";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests float literals for GoParser.
     */
    @Test
    public void testFloatLiterals1() {
        String src = "0.";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests float literals for GoParser.
     */
    @Test
    public void testFloatLiterals2() {
        String src = "072.40";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests float literals for GoParser.
     */
    @Test
    public void testFloatLiterals3() {
        String src = ".12345E+5";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

}