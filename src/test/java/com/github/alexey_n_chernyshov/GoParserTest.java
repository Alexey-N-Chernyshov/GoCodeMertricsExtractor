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

    /**
     * Tests imaginary literals for GoParser.
     */
    @Test
    public void testImaginaryLiterals1() {
        String src = "0i";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests imaginary literals for GoParser.
     */
    @Test
    public void testImaginaryLiterals2() {
        String src = "0.i";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests imaginary literals for GoParser.
     */
    @Test
    public void testImaginaryLiterals3() {
        String src = "1.e+0i";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests imaginary literals for GoParser.
     */
    @Test
    public void testImaginaryLiterals4() {
        String src = "6.67428e-11i";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests imaginary literals for GoParser.
     */
    @Test
    public void testImaginaryLiterals5() {
        String src = "1E6i";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests imaginary literals for GoParser.
     */
    @Test
    public void testImaginaryLiterals6() {
        String src = ".25i";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests rune literals for GoParser.
     */
    @Test
    public void testRuneLiterals1() {
        String src = "'a'";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests rune literals for GoParser.
     */
    @Test
    public void testRuneLiterals2() {
        String src = "'ä'";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests rune literals for GoParser.
     */
    @Test
    public void testRuneLiterals3() {
        String src = "'\\t'";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests rune literals for GoParser.
     */
    @Test
    public void testRuneLiterals4() {
        String src = "'\\007'";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests rune literals for GoParser.
     */
    @Test
    public void testRuneLiterals5() {
        String src = "'\\xff'";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests rune literals for GoParser.
     */
    @Test
    public void testRuneLiterals6() {
        String src = "'\\u12e4'";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests rune literals for GoParser.
     */
    @Test
    public void testRuneLiterals7() {
        String src = "'\\U00101234'";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests rune literals for GoParser.
     * rune literal containing single quote character.
     */
    @Test
    public void testRuneLiterals8() {
        String src = "'\\''";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests rune literals for GoParser.
     * illegal: too many characters
     */
    @Test(expected = TokenMgrError.class)
    public void testRuneLiterals9() throws ParseException {
        String src = "'aa'";
        GoParser parser = createParser(src);
        parser.Program();
    }

    /**
     * Tests rune literals for GoParser.
     * illegal: too few hexadecimal digits
     */
    @Test(expected = TokenMgrError.class)
    public void testRuneLiterals10() throws ParseException {
        String src = "'\\xa'";
        GoParser parser = createParser(src);
        parser.Program();
    }

    /**
     * Tests string literals for GoParser.
     */
    @Test
    public void testStringLiterals1() {
        String src = "`abc`";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests string literals for GoParser.
     */
    @Test
    public void testStringLiterals2() {
        String src =
                "`\\n\n" +
                        "\\n`";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests string literals for GoParser.
     */
    @Test
    public void testStringLiterals3() {
        String src = "\"Hello, world!\\n\"";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Tests string literals for GoParser.
     */
    @Test
    public void testStringLiterals4() {
        String src = "\"\\u65e5\\U00008a9e\"";
        GoParser parser = createParser(src);
        try {
            SimpleNode root = parser.Program();
            assertEquals(getParseResult(root), src);
        } catch (ParseException e) {
            fail("Caught an exception " + e.toString());
        }
    }
}