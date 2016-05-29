package com.itskyconsulting.floatmath;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Unit test for simple App.
 */
public class FloatMathExtTest {

    /** helper function: make sure that a function that would be +/- infinity mathematically is actually at least NaN, +/-Infinity or large by absolute value */
    private void assertInfinite(String s, double d, double r)  {
        if (Double.isInfinite(d)) {
            return;
        }
        if (Double.isInfinite(d)) {
            return;
        }
        assertTrue(s, Math.abs(d) > r);
    }

    /** helper function for testCotReg* */
    private void checkCotReg(int l, double delta) {
        for (int i = -l; i <= l; i++) {
            double d = i / 10.0 + 1e-3;
            double p = Math.tan(d) * FloatMathExt.cot(d);
            assertEquals("d=" + d + " p=" + p, 1.0, p, delta);
        }
    }

    /**
     * for values that are not multiples of pi/4 cot(x)*tan(x)=1
     */
    @Test
    public void testCotReg1() {
        int l = 500;
        double delta = 1e-10;
        checkCotReg(l, delta);
    }

    /**
     * for values that are not multiples of pi/4 cot(x)*tan(x)=1
     */
    @Test
    public void testCotReg2() {
        int l = 50;
        double delta = 1e-12;
        checkCotReg(l, delta);
    }

    /**
     * for values that are not multiples of pi/4 cot(x)*tan(x)=1
     */
    @Test
    public void testCotReg3() {
        int l = 5;
        double delta = 1e-13;
        checkCotReg(l, delta);
    }

    /**
     * for values that are odd multiples of pi/4 (x=pi/4+n*pi/2) cot(x)=0
     */
    @Test
    public void testCotSpecial() {
        for (int i = -50; i < 50; i++) {
            double d = i * Math.PI  + FloatMathExt.HALF_PI;
            double z = FloatMathExt.cot(d);
            assertEquals("d=" + d + " z=" + z, 0.0, z, 1e-12);
        }
    }

    /**
     * helper function for testCotBad*
     *
     * for values that are multiples of pi/2 (x=n*pi/2) cot(x) should be NaN or big in terms of absolute value
     */
    private void checkCotBad(int l, double r) {
        for (int i = -l; i < l; i++) {
            double d = i * Math.PI;
            double n = FloatMathExt.cot(d);
            System.out.println("i=" + i + " d=" + d + " n=" + n);
            assertInfinite("d=" + d + " n=" + n, n, r);
        }
    }

    /**
     * for values that are multiples of pi/2 (x=n*pi/2) cot(x) should be NaN or big in terms of absolute value
     */
    @Test
    public void testCotBad1() {
        int l = 500;
        double r = 1e10;
        checkCotBad(l, r);
    }

    /**
     * for values that are multiples of pi/2 (x=n*pi/2) cot(x) should be NaN or big in terms of absolute value
     */
    @Test
    public void testCotBad2() {
        int l = 50;
        double r = 1e13;
        checkCotBad(l, r);
    }

    /**
     * for values that are multiples of pi/2 (x=n*pi/2) cot(x) should be NaN or big in terms of absolute value
     */
    @Test
    public void testCotBad3() {
        int l = 5;
        double r = 1e14;
        checkCotBad(l, r);
    }

    /**
     * test of sec by multiplying sec(x)*cos(x) which should be 1
     */
    @Test
    public void testSecReg() {
        for (int i = -100; i <= 100; i++) {
            double d = i / 10.0 + 1e-3;
            double p = Math.cos(d) * FloatMathExt.sec(d);
            assertEquals("d=" + d + " p=" + p, 1.0, p, 1e-12);
        }
    }

    /**
     * test that sec(x) = sec(-x)
     */
    @Test
    public void testSecEven() {
        for (int i = 0; i <= 100; i++) {
            double d = i / 10.0 + 1e-3;
            double s1 = FloatMathExt.sec(d);
            double s2 = FloatMathExt.sec(-d);
            assertEquals("d=" + d + " s1=" + s1 + " s2=" + s2, s1, s2, 1e-100);
        }
    }

    /**
     *  test that csc(x) * sin(x) = 1
     */
    @Test
    public void testCscReg() {
        for (int i = 0; i < 100; i++) {
            double d = i / 10.0 + 1e-3;
            double p = Math.sin(d) * FloatMathExt.csc(d);
            assertEquals("d=" + d + " p=" + p, 1.0, p, 1e-12);
        }
    }

    /**
     *  test that csc(x) = - csc(-x)
     */
    @Test
    public void testCscOdd() {
        for (int i = 0; i <= 100; i++) {
            double d = i / 10.0 + 1e-3;
            double s1 = -FloatMathExt.csc(d);
            double s2 = FloatMathExt.csc(-d);
            assertEquals("d=" + d + " s1=" + s1 + " s2=" + s2, s1, s2, 1e-100);
        }
    }

    /**
     * helper for testAcotReg*
     *
     * test that within the right range acot(cot(x))=x
     */
    private void checkAcotReg(int l, int m, double delta) {
        double step = Math.PI / (2*l);
        for (int i = 0; i < l; i++) {
            double d0 = (i + 0.5) * step;
            for (int j = -m; j <= m; j++) {
                double d = d0 + Math.PI * j;
                double c = FloatMathExt.cot(d);
                double dd = FloatMathExt.acot(c);
                assertEquals("i=" + i + " j=" + j + " d0=" + d0 + " d=" + d + " c=" + c + " dd=" + dd, d0, dd, delta);
            }
        }
    }

    /**
     * test that within the right range acot(cot(x))=x
     */
    @Test
    public void testACotReg1() {
        int l = 500;
        double delta = 1e-10;
        checkAcotReg(l, l, delta);
    }

    /**
     * test that within the right range acot(cot(x))=x
     */
    @Test
    public void testACotReg2() {
        int l = 50;
        double delta = 1e-12;
        checkAcotReg(l, l, delta);
    }


    /**
     * test that within the right range acot(cot(x))=x
     */
    @Test
    public void testACotReg3() {
        int l = 5;
        double delta = 1e-13;
        checkAcotReg(l, l, delta);
    }

    /**
     * test that acot(0) = pi/2
     */
    @Test
    public void testACotSpecial() {
        double d = FloatMathExt.acot(0.0);
        assertEquals("d=" + d, FloatMathExt.HALF_PI, d, 1e-100);
    }


    /**
     * helper for testCotOfAcotReg*
     *
     * test that within the right range cot(acot(x)) = x
     */
    private void checkCotOfAcotReg(int l, double delta) {
        for (int i = -l; i < l; i++) {
            double d = i * 0.1;
            double a = FloatMathExt.acot(d);
            double dd = FloatMathExt.cot(a);
            assertEquals("i=" + i + " d=" + d + " a=" + a + " dd=" + dd, d, dd, delta);
        }
    }

    /**
     * test that within the right range cot(acot(x)) = x
     */
    @Test
    public void testCotOfACotReg1() {
        int l = 500;
        double delta = 1e-12;
        checkCotOfAcotReg(l, delta);
    }

    /**
     * test that within the right range cot(acot(x)) = x
     */
    @Test
    public void testCotOfACotReg2() {
        int l = 50;
        double delta = 1e-14;
        checkCotOfAcotReg(l, delta);
    }

    /**
     * test that within the right range cot(acot(x)) = x
     */
    @Test
    public void testCotOfACotReg3() {
        int l = 5;
        double delta = 1e-15;
        checkCotOfAcotReg(l, delta);
    }

    /**
     * test that within the right range asec(sec(x)) = x
     */
    @Test
    public void testASecOfSecReg() {
        for (int i = 0; i < 100; i++) {
            double d = Math.PI / 100 * i + Math.PI / 200;
            double s = FloatMathExt.sec(d);
            double dd = FloatMathExt.asec(s);
            assertEquals("i=" + i + " d=" + d + " s=" + s + " dd=" + dd, d, dd, 1e-14);
        }
    }

    /**
     * test that within the right range sec(asec(x)) = x
     */
    @Test
    public void testSecOfASecReg() {
        for (int i = -1000; i < 1000; i++) {
            double d = i / 10.0;
            if (Math.abs(d) < 1.0) {
                continue;
            }
            double a = FloatMathExt.asec(d);
            double dd = FloatMathExt.sec(a);
            assertEquals("i=" + i + " d=" + d + " a=" + a + " dd=" + dd, d, dd, 1e-11);
        }
    }

    /**
     * test that within the right range acsc(csc(x)) = x
     */
    @Test
    public void testACscOfCscReg() {
        for (int i = -50; i < 50; i++) {
            double d = Math.PI / 100 * i + Math.PI / 200;
            double s = FloatMathExt.csc(d);
            double dd = FloatMathExt.acsc(s);
            assertEquals("i=" + i + " d=" + d + " s=" + s + " dd=" + dd, d, dd, 1e-14);
        }
    }

    /**
     * test that within the right range csc(acsc(x)) = x
     */
    @Test
    public void testCscOfACscReg() {
        for (int i = -1000; i < 1000; i++) {
            double d = i / 10.0;
            if (Math.abs(d) < 1.0) {
                continue;
            }
            double a = FloatMathExt.acsc(d);
            double dd = FloatMathExt.csc(a);
            assertEquals("i=" + i + " d=" + d + " a=" + a + " dd=" + dd, d, dd, 1e-11);
        }
    }

}
