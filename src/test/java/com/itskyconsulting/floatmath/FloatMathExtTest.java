// -*- coding: utf-8-unix -*- Юникод/UTF-8

// (C) Karl Brodowsky 2016
// GNU LESSER GENERAL PUBLIC LICENSE, Version 2.1 of February 1999

package com.itskyconsulting.floatmath;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.itskyconsulting.floatmath.FloatMathExt.*;

/**
 * Unit tests for simple FloatMathExt
 */
public class FloatMathExtTest {

    /** helper function: make sure that a function that would be +/- infinity mathematically is actually +/-Infinity or large by absolute value */
    private void assertBigOrInfinite(String s, double d, double r)  {
        if (Double.isInfinite(d)) {
            return;
        }
        if (Double.isNaN(d)) {
            fail(s + " d=NaN");
        }
        assertTrue(s, Math.abs(d) > r);
    }

    /** helper function: make sure that a function that would be +/- infinity mathematically is actually +/-Infinity or large by absolute value */
    private void assertNaN(String s, double d) {
        assertTrue(s + " d=" + d, Double.isNaN(d));
    }

    /** helper function: make sure that a function that would be +infinity  */
    private void assertPostiveInfinity(String s, double d) {
        String ss = s + " d=" + d;
        assertTrue(ss, d > 0);
        assertTrue(s + " d=" + d, Double.isInfinite(d));
    }

    /** helper function: make sure that a function that would be +infinity  */
    private void assertNegativeInfinity(String s, double d) {
        String ss = s + " d=" + d;
        assertTrue(ss, d < 0);
        assertTrue(s + " d=" + d, Double.isInfinite(d));
    }

    /** helper function for testCotReg* */
    private void checkCotReg(int l, double delta) {
        for (int i = -l; i <= l; i++) {
            double d = i / 10.0 + 1e-3;
            double p = Math.tan(d) * cot(d);
            assertEquals("d=" + d + " p=" + p, 1.0, p, delta);
        }
    }

    /**
     * for values that are not multiples of pi/4 cot(x)*tan(x)=1
     */
    @Test
    public void testCotReg1() {
        checkCotReg(500, 1e-10);
    }

    /**
     * for values that are not multiples of pi/4 cot(x)*tan(x)=1
     */
    @Test
    public void testCotReg2() {
        checkCotReg(50, 1e-12);
    }

    /**
     * for values that are not multiples of pi/4 cot(x)*tan(x)=1
     */
    @Test
    public void testCotReg3() {
        checkCotReg(5, 1e-13);
    }

    /**
     * for values that are odd multiples of pi/4 (x=pi/4+n*pi/2) cot(x)=0
     */
    @Test
    public void testCotSpecial() {
        for (int i = -50; i < 50; i++) {
            double d = i * Math.PI  + HALF_PI;
            double z = cot(d);
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
            double n = cot(d);
            assertBigOrInfinite("d=" + d + " n=" + n, n, r);
        }
    }

    /**
     * for values that are multiples of pi/2 (x=n*pi/2) cot(x) should be NaN or big in terms of absolute value
     */
    @Test
    public void testCotBad1() {
        checkCotBad(500, 1e10);
    }

    /**
     * for values that are multiples of pi/2 (x=n*pi/2) cot(x) should be NaN or big in terms of absolute value
     */
    @Test
    public void testCotBad2() {
        checkCotBad(50, 1e13);
    }

    /**
     * for values that are multiples of pi/2 (x=n*pi/2) cot(x) should be NaN or big in terms of absolute value
     */
    @Test
    public void testCotBad3() {
        checkCotBad(5, 1e14);
    }

    /**
     * test that cot(-x)=-cot(x)
     */
    @Test
    public void testCotOdd() {
        for (int i = 0; i < 100; i++) {
            double d = (i+0.5) * Math.PI / 100.0;
            double s1 = -cot(d);
            double s2 = cot(-d);
            assertEquals("d=" + d + " s1=" + s1 + " s2=" + s2, s1, s2, 1e-100);
        }
    }

    /**
     * test of sec by multiplying sec(x)*cos(x) which should be 1
     */
    @Test
    public void testSecReg() {
        for (int i = -100; i <= 100; i++) {
            double d = i / 10.0 + 1e-3;
            double p = Math.cos(d) * sec(d);
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
            double s1 = sec(d);
            double s2 = sec(-d);
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
            double p = Math.sin(d) * csc(d);
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
            double s1 = -csc(d);
            double s2 = csc(-d);
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
                double c = cot(d);
                double dd = acot(c);
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
        double d = acot(0.0);
        assertEquals("d=" + d, HALF_PI, d, 1e-100);
    }


    /**
     * helper for testCotOfAcotReg*
     *
     * test that within the right range cot(acot(x)) = x
     */
    private void checkCotOfAcotReg(int l, double delta) {
        for (int i = -l; i < l; i++) {
            double d = i * 0.1;
            double a = acot(d);
            double dd = cot(a);
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

    @Test
    public void testAtanOdd() {
        for (int i = 0; i <= 1000; i++) {
            double d = i / 10.0;
            double a1 = -Math.atan(d);
            double a2 = Math.atan(-d);
            assertEquals(a1, a2, 1e-100);
        }
    }

    @Test
    public void testAcotAlmostOdd() {
        for (int i = 0; i <= 1000; i++) {
            double d = i / 10.0;
            double a1 = Math.PI-acot(d);
            double a2 = acot(-d);
            assertEquals(a1, a2, 1e-100);
        }
    }

    /**
     * test that within the right range asec(sec(x)) = x
     */
    @Test
    public void testASecOfSecReg() {
        for (int i = 0; i < 100; i++) {
            double d = Math.PI / 100 * i + Math.PI / 200;
            double s = sec(d);
            double dd = asec(s);
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
            double a = asec(d);
            double dd = sec(a);
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
            double s = csc(d);
            double dd = acsc(s);
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
            double a = acsc(d);
            double dd = csc(a);
            assertEquals("i=" + i + " d=" + d + " a=" + a + " dd=" + dd, d, dd, 1e-11);
        }
    }

    /**
     * test that within the right range csc(acsc(x)) = x
     */
    @Test
    public void testCscOfACscOdd() {
        for (int i = -1000; i < 1000; i++) {
            double d = i / 10.0;
            if (Math.abs(d) < 1.0) {
                continue;
            }
            double a1 = -acsc(d);
            double a2 = acsc(-d);
            assertEquals("i=" + i + " d=" + d + " a1=" + a1 + " a2=" + a2, a1, a2, 1e-100);
        }
    }

    /** test sind for some well known values */
    @Test
    public void testSind() {
        assertEquals(0.0, sind(0), 1e-100);
        assertEquals(0.5, sind(30), 1e-16);
        assertEquals(Math.sqrt(2)/2, sind(45), 1e-15);
        assertEquals(Math.sqrt(3)/2, sind(60), 1e-20);
        assertEquals(1.0, sind(90), 1e-100);
    }

    /** test cosd for some well known values */
    @Test
    public void testCosd() {
        assertEquals(0.0, cosd(90), 1e-16);
        assertEquals(0.5, cosd(60), 1e-15);
        assertEquals(Math.sqrt(2)/2, cosd(45), 1e-15);
        assertEquals(Math.sqrt(3)/2, cosd(30), 1e-15);
        assertEquals(1.0, cosd(0), 1e-100);
    }

    /** test tand for some well known values */
    @Test
    public void testTand() {
        assertEquals(-Math.sqrt(3), tand(-60), 1e-15);
        assertEquals(-1.0, tand(-45), 1e-15);
        assertEquals(-Math.sqrt(3)/3, tand(-30), 1e-100);
        assertEquals(0.0, tand(0), 1e-100);
        assertEquals(Math.sqrt(3)/3, tand(30), 1e-100);
        assertEquals(1.0, tand(45), 1e-15);
        assertEquals(Math.sqrt(3), tand(60), 1e-15);
    }

    /** test cotd for some well known values */
    @Test
    public void testCotd() {
        assertEquals(Math.sqrt(3), cotd(30), 1e-15);
        assertEquals(1.0, cotd(45), 1e-15);
        assertEquals(Math.sqrt(3)/3, cotd(60), 1e-100);
        assertEquals(0.0, cotd(90), 1e-100);
        assertEquals(-Math.sqrt(3)/3, cotd(120), 1e-100);
        assertEquals(-1.0, cotd(135), 1e-15);
        assertEquals(-Math.sqrt(3), cotd(150), 1e-15);
    }

    /** test secd for some well known values */
    @Test
    public void testSecd() {
        assertEquals(2.0, secd(60), 1e-15);
        assertEquals(Math.sqrt(2), secd(45), 1e-15);
        assertEquals(2*Math.sqrt(3)/3, secd(30), 1e-15);
        assertEquals(1.0, secd(0), 1e-100);
    }

    /** test cscd for some well known values */
    @Test
    public void testCscd() {
        assertEquals(2.0, cscd(30), 1e-15);
        assertEquals(Math.sqrt(2), cscd(45), 1e-15);
        assertEquals(2*Math.sqrt(3)/3, cscd(60), 1e-15);
        assertEquals(1.0, cscd(90), 1e-100);
    }

    /** test asind for some well known values */
    @Test
    public void testAsind() {
        assertEquals(0, asind(0.0), 1e-100);
        assertEquals(30, asind(0.5), 1e-14);
        assertEquals(45, asind(Math.sqrt(2)/2), 1e-14);
        assertEquals(60, asind(Math.sqrt(3)/2), 1e-14);
        assertEquals(90, asind(1.0), 1e-100);
    }

    /** test acosd for some well known values */
    @Test
    public void testAcosd() {
        assertEquals(90, acosd(0.0), 1e-16);
        assertEquals(60, acosd(0.5), 1e-14);
        assertEquals(45, acosd(Math.sqrt(2)/2), 1e-15);
        assertEquals(30, acosd(Math.sqrt(3)/2), 1e-14);
        assertEquals(0, acosd(1.0), 1e-100);
    }

    /** test atand for some well known values */
    @Test
    public void testAtand() {
        assertEquals(-60, atand(-Math.sqrt(3)), 1e-14);
        assertEquals(-45, atand(-1.0), 1e-15);
        assertEquals(-30, atand(-Math.sqrt(3)/3), 1e-14);
        assertEquals(0, atand(0.0), 1e-100);
        assertEquals(30, atand(Math.sqrt(3)/3), 1e-14);
        assertEquals(45, atand(1.0), 1e-15);
        assertEquals(60, atand(Math.sqrt(3)), 1e-14);
    }

    /** test acotd for some well known values */
    @Test
    public void testAcotd() {
        assertEquals(30, acotd(Math.sqrt(3)), 1e-14);
        assertEquals(45, acotd(1.0), 1e-15);
        assertEquals(60, acotd(Math.sqrt(3)/3), 1e-100);
        assertEquals(90, acotd(0.0), 1e-100);
        assertEquals(120, acotd(-Math.sqrt(3)/3), 1e-100);
        assertEquals(135, acotd(-1.0), 1e-15);
        assertEquals(150, acotd(-Math.sqrt(3)), 1e-15);
    }

    /** test asecd for some well known values */
    @Test
    public void testAsecd() {
        assertEquals(60, asecd(2.0), 1e-14);
        assertEquals(45, asecd(Math.sqrt(2)), 1e-14);
        assertEquals(30, asecd(2*Math.sqrt(3)/3), 2e-14);
        assertEquals(0, asecd(1.0), 1e-100);
    }

    /** test acscd for some well known values */
    @Test
    public void testAcscd() {
        assertEquals(30, acscd(2.0), 1e-14);
        assertEquals(45, acscd(Math.sqrt(2)), 1e-14);
        assertEquals(60, acscd(2*Math.sqrt(3)/3), 1e-14);
        assertEquals(90, acscd(1.0), 1e-100);
    }

    /** test sind(-x)=-sind(x) */
    @Test
    public void testSindOdd() {
        for (int i = 0; i < 360; i++) {
            assertEquals(-sind(i), sind(-i), 1e-100);
        }
    }

    /** test cosd(-x) = cosd(x) */
    @Test
    public void testCosdEven() {
        for (int i = 0; i < 360; i++) {
            assertEquals(cosd(i), cosd(-i), 1e-100);
        }
    }

    /** test tand(-x)=-tand(x) */
    @Test
    public void testTandOdd() {
        for (int i = 0; i < 90; i++) {
            assertEquals(-tand(i), tand(-i), 1e-100);
        }
    }

    /** test cotd(-x) = -cotd(x) */
    @Test
    public void testCotdOdd() {
        for (int i = 1; i < 180; i++) {
            assertEquals(-cotd(i), cotd(-i), 1e-100);
        }
    }

    /** test secd(-x) = -secd(x) */
    @Test
    public void testSecdEven() {
        for (int i = 0; i < 360; i++) {
            if (i == 90 || i == 270) {
                continue;
            }
            assertEquals(secd(i), secd(-i), 1e-100);
        }
    }

    /** test cscd(-x) = -cscd(x) */
    @Test
    public void testCscdOdd() {
        for (int i = 1; i < 360; i++) {
            if (i == 180) {
                continue;
            }
            assertEquals(-cscd(i), cscd(-i), 1e-100);
        }
    }

    /** test asind(-x) = -asind(x) */
    @Test
    public void testAsindOdd() {
        for (int i = 0; i <= 10; i++) {
            double d = i / 10.0;
            assertEquals(-asind(d), asind(-d), 1e-100);
        }
    }

    /** test atand(-x) = -atand(x) */
    @Test
    public void testAtandOdd() {
        for (int i = 0; i <= 1000; i++) {
            double d = i / 10.0;
            assertEquals(-asind(d), asind(-d), 1e-100);
        }
    }

    /** test acotd(-x)=180-acotd(x) */
    @Test
    public void testAcotdAlmostOdd() {
        for (int i = 0; i <= 1000; i++) {
            double d = i / 10.0;
            assertEquals(180-acotd(d), acotd(-d), 1e-100);
        }
    }

    /** test acscd for some well known values */
    @Test
    public void testAcscdOdd() {
        for (int i = 0; i <= 1000; i++) {
            if (-10 < i && i < 10) {
                continue;
            }
            double d = i / 10.0;
            assertEquals(-acsc(d), acsc(-d), 1e-100);
        }
    }

    /** test coth(x)*tanh(x) = 1 for x != 0 */
    @Test
    public void testCoth() {
        for (int i = -3; i <= 3; i++) {
            if (i == 0) {
                continue;
            }
            double x = i;
            assertEquals("i=" + i + " x=" + x, 1.0, Math.tanh(x) * coth(x), 1e-14);
        }
    }

    /** test sech(x)*cos(x) = 1 */
    @Test
    public void testSech() {
        for (int i = -3; i <= 3; i++) {
            double x = i;
            assertEquals("i=" + i + " x=" + x, 1.0, Math.cosh(x) * sech(x), 1e-15);
        }
    }

    /** test csch(x)*sinh(x) = 1 for x != 0 */
    @Test
    public void testCsch() {
        for (int i = -3; i <= 3; i++) {
            if (i == 0) {
                continue;
            }
            double x = i;
            assertEquals("i=" + i + " x=" + x, 1.0, Math.sinh(x) * csch(x), 1e-20);
        }
    }

    /** test sinh(-x) = -sinh(x) (for consistency */
    @Test
    public void testSinhOdd() {
        for (int i = 0; i < 1000; i++) {
            double d = i/10.0;
            assertEquals(-Math.sinh(d), Math.sinh(-d), 1e-100);
        }
    }

    /** test cosh(-x) = cosh(x) (for consistency */
    @Test
    public void testSinhEven() {
        for (int i = 0; i < 1000; i++) {
            double d = i/10.0;
            assertEquals(Math.cosh(d), Math.cosh(-d), 1e-100);
        }
    }

    /** test tanh(-x) = -tanh(x) (for consistency */
    @Test
    public void testTanhOdd() {
        for (int i = 0; i < 1000; i++) {
            double d = i/10.0;
            assertEquals(-Math.tanh(d), Math.tanh(-d), 1e-100);
        }
    }

    /** test coth(-x) = -coth(x) (for consistency */
    @Test
    public void testCothOdd() {
        for (int i = 1; i < 1000; i++) {
            double d = i/10.0;
            assertEquals(-coth(d), coth(-d), 1e-100);
        }
    }

    /** test sech(-x) = sech(x) (for consistency */
    @Test
    public void testSechEven() {
        for (int i = 1; i < 1000; i++) {
            double d = i/10.0;
            assertEquals(sech(d), sech(-d), 1e-100);
        }
    }

    /** test csch(-x) = -csch(x) (for consistency */
    @Test
    public void testCschOdd() {
        for (int i = 1; i < 1000; i++) {
            double d = i/10.0;
            assertEquals(-csch(d), csch(-d), 1e-100);
        }
    }

    /** test asinh(sinh(x)) = x */
    @Test
    public void testAsinhOfSinh() {
        for (int i = -3; i <= 3; i++) {
            double x = i;
            assertEquals("i=" + i + " x=" + x, x, asinh(Math.sinh(x)), 2e-14);
        }

    }

    /** test sinh(asinh(x)) = x */
    @Test
    public void testSinhOfAsinh() {
        for (int i = -100; i <= 100; i++) {
            double x = i/10.0;
            assertEquals("i=" + i + " x=" + x, x, Math.sinh(asinh(x)), 1e-14);
        }
    }

    /** test asinh(-x) = -asinh(x) */
    @Test
    public void testAsinhOdd() {
        for (int i = 0; i <= 100; i++) {
            double x = i/10.0;
            assertEquals(-asinh(x), asinh(-x), 1e-100);
        }
    }

    /** test acosh(cosh(x)) = |x| */
    @Test
    public void testAcoshOfCosh() {
        for (int i = -3; i <= 3; i++) {
            double x = i;
            assertEquals("i=" + i + " x=" + x, Math.abs(x), acosh(Math.cosh(x)), 2e-14);
        }

    }

    /** test cosh(acosh(x)) = x for x >= 1 */
    @Test
    public void testCoshOfAcosh() {
        for (int i = 1; i <= 10; i++) {
            double x = i;
            assertEquals("i=" + i + " x=" + x, x, Math.cosh(acosh(x)), 1e-14);
        }

    }

    /** test acosh(x) = NaN for x < 1 */
    @Test
    public void testBadAcosh() {
        for (int i = -100; i < 10; i++) {
            double x = i / 10.0;
            assertNaN("i=" + i + " x=" + x, acosh(x));
        }
    }

    /** test atanh(tanh(x))=x */
    @Test
    public void testAtanhOfTanh() {
        for (int i = -3; i <= 3; i++) {
            double x = i;
            assertEquals("i=" + i + " x=" + x, x, atanh(Math.tanh(x)), 2e-14);
        }

    }

    /** test tanh(atanh(x))=x for -1 < x < 1 */
    @Test
    public void testTanhOfAtanh() {
        for (int i = -999; i <= 999; i++) {
            double x = i / 1000.0;
            double a = atanh(x);
            assertEquals("i=" + i + " x=" + x + " a=" + a, x, Math.tanh(a), 1.7e-16);
        }
    }

    /** test atanh(x) = NaN for x <= -1 or x >= 1 */
    @Test
    public void testBadAtanh() {
        for (int i = -50; i <= 50; i++) {
            if (-10 < i && i < 10) {
                continue;
            }
            double x = i / 10.0;
            assertNaN("i=" + i + " x=" + x, atanh(x));
        }
    }

    /** test atanh(-x) = -atanh(x) */
    @Test
    public void testAtanhOdd() {
        for (int i = 0; i < 100; i++) {
            double x = i/100.0;
            assertEquals(-atanh(x), atanh(-x), 1e-100);
        }
    }

    /** test acoth(coth(x)) = x for x != 0 */
    @Test
    public void testAcothOfCoth() {
        for (int i = -5; i <= 5; i++) {
            if (i == 0) {
                continue;
            }
            double x = i;
            double t = coth(x);
            assertEquals("i=" + i + " x=" + x + " t=" + t, x, acoth(t), 1e-12);
        }

    }

    /** test coth(acoth(x)) = x for x < -1 or x > 1 */
    @Test
    public void testCothOfAcoth() {
        for (int i = -100; i <= 100; i++) {
            if (-10 <= i && i <= 10) {
                continue;
            }
            double x = i / 10.0;
            double a = acoth(x);
            assertEquals("i=" + i + " x=" + x + " a=" + a, x, coth(a), 2e-14);
        }
    }

    /** test acoth(x) = NaN for -1 <= x <= 1 */
    @Test
    public void testBadAcoth() {
        for (int i = -10; i <= 10; i++) {
            double x = i / 10.0;
            assertNaN("i=" + i + " x=" + x, acoth(x));
        }
    }

    /** test acoth(-x) = -acoth(x) */
    @Test
    public void testAcothOdd() {
        for (int i = 100; i > 10; i--) {
            double x = i/10.0;
            assertEquals(-acoth(x), acoth(-x), 1e-100);
        }
    }

    /** test asech(sech(x)) = x */
    @Test
    public void testAsechOfSech() {
        for (int i = -3; i <= 3; i++) {
            double x = i;
            double s = sech(x);
            assertEquals("i=" + i + " x=" + x + " s=" + s, Math.abs(x), asech(s), 2e-14);
        }

    }

    /** test sech(asech(x)) = x for 0 < x <= 1 */
    @Test
    public void testSechOfAsech() {
        for (int i = 1; i <= 10; i++) {
            double x = i / 10.0;
            double a = asech(x);
            assertEquals("i=" + i + " x=" + x + " a=" + a, x, sech(a), 1e-14);
        }

    }

    /** test asech(x) = NaN for x <= 0 or x > 1 */
    @Test
    public void testBadAsech() {
        for (int i = -50; i <= 50; i++) {
            if (0 < i && i <= 10) {
                continue;
            }
            double x = i / 10.0;
            assertNaN("i=" + i + " x=" + x, asech(x));
        }
    }

    /** test acsch(csch(x)) = x */
    @Test
    public void testAcschOfCsch() {
        for (int i = -3; i <= 3; i++) {
            if (i == 0) {
                continue;
            }
            double x = i;
            double s = csch(x);
            assertEquals("i=" + i + " x=" + x + " s=" + s, x, acsch(s), 2e-14);
        }

    }

    /** test csch(acsch(x)) = x for x != 0 */
    @Test
    public void testCschOfAcsch() {
        for (int i = -50; i <= 50; i++) {
            if (i == 0) {
                continue;
            }
            double x = i / 10.0;
            double a = acsch(x);
            assertEquals("i=" + i + " x=" + x + " a=" + a, x, csch(a), 1e-14);
        }

    }

    /** test acsc(0) = NaN */
    @Test
    public void testBadAcsch() {
        assertNaN("x=0", acsch(0));
    }


    /** test acsch(-x) = -acsch(x) */
    @Test
    public void testAcschOdd() {
        for (int i = 1; i < 100; i++) {
            double x = i/10.0;
            assertEquals(-acsch(x), acsch(-x), 1e-100);
        }
    }

    /** test log2(exp2(x))=x */
    @Test
    public void testLog2OfExp2() {
        for (int i = -100; i <= 100; i++) {
            double d = i / 10.0;
            assertEquals("i=" + i + " d=" + d, d, log2(exp2(d)), 1e-12);
        }
    }

    /** test exp2(log2(x))=x for x > 0 */
    @Test
    public void testExp2OfLog2() {
        for (int i =  1; i <= 100; i++) {
            double d = i / 10.0;
            assertEquals("i=" + i + " d=" + d, d, exp2(log2(d)), 1e-12);
        }
    }

    /** test log10(exp10(x))=x */
    @Test
    public void testLog10OfExp10() {
        for (int i = -100; i <= 100; i++) {
            double d = i / 10.0;
            assertEquals("i=" + i + " d=" + d, d, log10(exp10(d)), 1e-12);
        }
    }

    /** test exp10(log10(x))=x for x > 0 */
    @Test
    public void testExp10OfLog10() {
        for (int i =  1; i <= 100; i++) {
            double d = i / 10.0;
            assertEquals("i=" + i + " d=" + d, d, exp10(log10(d)), 1e-12);
        }
    }

    /** make sure that all functions return NaN when called with NaN */
    @Test
    public void testNaN() {
        assertNaN("cot(NaN)", cot(Double.NaN));
        assertNaN("sec(NaN)", sec(Double.NaN));
        assertNaN("csc(NaN)", csc(Double.NaN));
        assertNaN("acot(NaN)", acot(Double.NaN));
        assertNaN("asec(NaN)", asec(Double.NaN));
        assertNaN("acsc(NaN)", acsc(Double.NaN));
        assertNaN("sind(NaN)", sind(Double.NaN));
        assertNaN("cosd(NaN)", cosd(Double.NaN));
        assertNaN("tand(NaN)", tand(Double.NaN));
        assertNaN("cotd(NaN)", cotd(Double.NaN));
        assertNaN("secd(NaN)", secd(Double.NaN));
        assertNaN("cscd(NaN)", cscd(Double.NaN));
        assertNaN("asind(NaN)", asind(Double.NaN));
        assertNaN("acosd(NaN)", acosd(Double.NaN));
        assertNaN("atand(NaN)", atand(Double.NaN));
        assertNaN("acotd(NaN)", acotd(Double.NaN));
        assertNaN("asecd(NaN)", asecd(Double.NaN));
        assertNaN("acscd(NaN)", acscd(Double.NaN));
        assertNaN("coth(NaN)", coth(Double.NaN));
        assertNaN("sech(NaN)", sech(Double.NaN));
        assertNaN("csch(NaN)", csch(Double.NaN));
        assertNaN("asinh(NaN)", asinh(Double.NaN));
        assertNaN("acosh(NaN)", acosh(Double.NaN));
        assertNaN("atanh(NaN)", atanh(Double.NaN));
        assertNaN("acoth(NaN)", acoth(Double.NaN));
        assertNaN("asech(NaN)", asech(Double.NaN));
        assertNaN("acsch(NaN)", acsch(Double.NaN));
        assertNaN("log10(NaN)", log10(Double.NaN));
        assertNaN("log2(NaN)", log2(Double.NaN));
        assertNaN("exp10(NaN)", exp10(Double.NaN));
        assertNaN("exp2(NaN)", exp2(Double.NaN));
    }

    /** make sure that all functions return the correct value when called with +Infinity */
    @Test
    public void testPosInfinity() {
        // periodic functions return NaN when called with Infinty
        // Math-lib functions to check uniformity of this library with standard library
        assertNaN("sin(Infinity)", Math.sin(Double.POSITIVE_INFINITY));
        assertNaN("cos(Infinity)", Math.cos(Double.POSITIVE_INFINITY));
        assertNaN("tan(Infinity)", Math.tan(Double.POSITIVE_INFINITY));

        assertNaN("cot(Infinity)", cot(Double.POSITIVE_INFINITY));
        assertNaN("sec(Infinity)", sec(Double.POSITIVE_INFINITY));
        assertNaN("csc(Infinity)", csc(Double.POSITIVE_INFINITY));

        // Math-lib functions to check uniformity of this library with standard library
        // sin and cos have values only between -1 and 1, so asin and acos cannot work for Infinity
        assertNaN("asin(Infinity)", Math.asin(Double.POSITIVE_INFINITY));
        assertNaN("acos(Infinity)", Math.acos(Double.POSITIVE_INFINITY));

        // tan(x) -> infinity for x-> pi/2 from below:
        assertEquals("atan(Infinity)", FloatMathExt.HALF_PI, Math.atan(Double.POSITIVE_INFINITY), 1e-100);

        assertEquals("acot(Infinity)", Math.atan(0), acot(Double.POSITIVE_INFINITY), 1e-100);
        assertEquals("asec(Infinity)", Math.acos(0), asec(Double.POSITIVE_INFINITY), 1e-100);
        assertEquals("acsc(Infinity)", Math.asin(0), acsc(Double.POSITIVE_INFINITY), 1e-100);

        // periodic functions return NaN when called with Infinty
        assertNaN("sind(Infinity)", sind(Double.POSITIVE_INFINITY));
        assertNaN("cosd(Infinity)", cosd(Double.POSITIVE_INFINITY));
        assertNaN("tand(Infinity)", tand(Double.POSITIVE_INFINITY));
        assertNaN("cotd(Infinity)", cotd(Double.POSITIVE_INFINITY));
        assertNaN("secd(Infinity)", secd(Double.POSITIVE_INFINITY));
        assertNaN("cscd(Infinity)", cscd(Double.POSITIVE_INFINITY));

        // sin and cos have values only between -1 and 1, so asin and acos cannot work for Infinity
        assertNaN("asind(Infinity)", asind(Double.POSITIVE_INFINITY));
        assertNaN("acosd(Infinity)", acosd(Double.POSITIVE_INFINITY));

        // see atan
        assertEquals("atand(Infinity)", 90, atand(Double.POSITIVE_INFINITY), 1e-100);
        // cot(x) -> Infinity for x-> 0 from above
        assertEquals("acotd(Infinity)", 0, acotd(Double.POSITIVE_INFINITY), 1e-100);
        assertEquals("asecd(Infinity)", acosd(0), asecd(Double.POSITIVE_INFINITY), 1e-100);
        assertEquals("acscd(Infinity)", asind(0), acscd(Double.POSITIVE_INFINITY), 1e-100);

        // Math-lib functions to check uniformity of this library with standard library
        assertPostiveInfinity("sinh(Infinity)", Math.sinh(Double.POSITIVE_INFINITY));
        assertPostiveInfinity("cosh(Infinity)", Math.cosh(Double.POSITIVE_INFINITY));
        assertEquals("tanh(Infinity)", 1, Math.tanh(Double.POSITIVE_INFINITY), 1e-100);

        // see sinh, cosh, tanh
        assertEquals("coth(Infinity)", 1, coth(Double.POSITIVE_INFINITY), 1e-100);
        assertEquals("sech(Infinity)", 0, sech(Double.POSITIVE_INFINITY), 1e-100);
        assertEquals("csch(Infinity)", 0, csch(Double.POSITIVE_INFINITY), 1e-100);

        // see sinh and cosh
        assertPostiveInfinity("asinh(Infinity)", asinh(Double.POSITIVE_INFINITY));
        assertPostiveInfinity("acosh(Infinity)", acosh(Double.POSITIVE_INFINITY));

        // tanh has values in range from -1 to 1, so Infinity does not occur.
        assertNaN("atanh(Infinity)", atanh(Double.POSITIVE_INFINITY));
        // coth(x) -> Infinity for x -> 0 from above
        assertEquals("acoth(Infinity)", 0, acoth(Double.POSITIVE_INFINITY), 1e-100);
        // sech has values in range from 0 to 1, so Infinity does not occur.
        assertNaN("asech(Infinity)", asech(Double.POSITIVE_INFINITY));
        // csch(x) -> Infinity for x->0 from above
        assertEquals("acsch(Infinity)", 0, acsch(Double.POSITIVE_INFINITY), 1e-100);

        // all three have f(x)-> Infinity for x-> Infinity
        assertPostiveInfinity("log10(Infinity)", log10(Double.POSITIVE_INFINITY));
        assertPostiveInfinity("log2(Infinity)", log2(Double.POSITIVE_INFINITY));
        assertPostiveInfinity("exp10(Infinity)", exp10(Double.POSITIVE_INFINITY));
        assertPostiveInfinity("exp2(Infinity)", exp2(Double.POSITIVE_INFINITY));
    }

    /** make sure that all functions return the correct value when called with +Infinity */
    @Test
    public void testNegInfinity() {
        // periodic functions return NaN when called with Infinty
        // include Math-functions to make sure that the new functions conform with them
        assertNaN("sin(-Infinity)", Math.sin(Double.NEGATIVE_INFINITY));
        assertNaN("cos(-Infinity)", Math.cos(Double.NEGATIVE_INFINITY));
        assertNaN("tan(-Infinity)", Math.tan(Double.NEGATIVE_INFINITY));

        assertNaN("cot(-Infinity)", cot(Double.NEGATIVE_INFINITY));
        assertNaN("sec(-Infinity)", sec(Double.NEGATIVE_INFINITY));
        assertNaN("csc(-Infinity)", csc(Double.NEGATIVE_INFINITY));

        // Math-lib functions to check uniformity of this library with standard library
        // sin and cos have values only between -1 and 1, so asin and acos cannot work for Infinity
        assertNaN("asin(-Infinity)", Math.asin(Double.NEGATIVE_INFINITY));
        assertNaN("acos(-Infinity)", Math.acos(Double.NEGATIVE_INFINITY));
        // tan(x) -> -infinity for x-> -pi/2 from above
        assertEquals("atan(-Infinity)", -FloatMathExt.HALF_PI, Math.atan(Double.NEGATIVE_INFINITY), 1e-100);

        // cot(x) -> -infinity for x-> -pi from below
        assertEquals("acot(-Infinity)", Math.PI, acot(Double.NEGATIVE_INFINITY), 1e-100);
        // see cos and sin
        assertEquals("asec(-Infinity)", Math.acos(0), asec(Double.NEGATIVE_INFINITY), 1e-100);
        assertEquals("acsc(-Infinity)", Math.asin(0), acsc(Double.NEGATIVE_INFINITY), 1e-100);

        // periodic functions return NaN when called with -Infinty
        assertNaN("sind(-Infinity)", sind(Double.NEGATIVE_INFINITY));
        assertNaN("cosd(-Infinity)", cosd(Double.NEGATIVE_INFINITY));
        assertNaN("tand(-Infinity)", tand(Double.NEGATIVE_INFINITY));
        assertNaN("cotd(-Infinity)", cotd(Double.NEGATIVE_INFINITY));
        assertNaN("secd(-Infinity)", secd(Double.NEGATIVE_INFINITY));
        assertNaN("cscd(-Infinity)", cscd(Double.NEGATIVE_INFINITY));

        // sin and cos have values only between -1 and 1, so asin and acos cannot work for Infinity
        assertNaN("asind(-Infinity)", asind(Double.NEGATIVE_INFINITY));
        assertNaN("acosd(-Infinity)", acosd(Double.NEGATIVE_INFINITY));

        // see atan, acot, asec, acsc
        assertEquals("atand(-Infinity)", -90, atand(Double.NEGATIVE_INFINITY), 1e-100);
        assertEquals("acotd(-Infinity)", 180, acotd(Double.NEGATIVE_INFINITY), 1e-100);
        assertEquals("asecd(-Infinity)", acosd(0), asecd(Double.NEGATIVE_INFINITY), 1e-100);
        assertEquals("acscd(-Infinity)", asind(0), acscd(Double.NEGATIVE_INFINITY), 1e-100);

        // Math-lib functions to check uniformity of this library with standard library
        assertNegativeInfinity("sinh(-Infinity)", Math.sinh(Double.NEGATIVE_INFINITY));
        assertPostiveInfinity("cosh(-Infinity)", Math.cosh(Double.NEGATIVE_INFINITY));
        assertEquals("tanh(-Infinity)", -1, Math.tanh(Double.NEGATIVE_INFINITY), 1e-100);

        // see sinh, cosh, tanh
        assertEquals("coth(-Infinity)", -1, coth(Double.NEGATIVE_INFINITY), 1e-100);
        assertEquals("sech(-Infinity)", 0, sech(Double.NEGATIVE_INFINITY), 1e-100);
        assertEquals("csch(-Infinity)", 0, csch(Double.NEGATIVE_INFINITY), 1e-100);

        // see sinh
        assertNegativeInfinity("asinh(-Infinity)", asinh(Double.NEGATIVE_INFINITY));
        // negative values do not occur for cosh
        assertNaN("acosh(-Infinity)", acosh(Double.NEGATIVE_INFINITY));
        // tanh has values in range from -1 to 1, so Infinity does not occur.
        assertNaN("atanh(-Infinity)", atanh(Double.NEGATIVE_INFINITY));
        // coth(x) -> -Infinity for x -> 0 from below
        assertEquals("acoth(-Infinity)", 0, acoth(Double.NEGATIVE_INFINITY), 1e-100);
        // sech has values in range from 0 to 1, so -Infinity does not occur.
        assertNaN("asech(-Infinity)", asech(Double.NEGATIVE_INFINITY));
        // csch(x) -> -Infinity for x->0 from below
        assertEquals("acsch(-Infinity)", 0, acsch(Double.NEGATIVE_INFINITY), 1e-100);

        // logarithms are not defined for negative arguments
        assertNaN("log10(-Infinity)", log10(Double.NEGATIVE_INFINITY));
        assertNaN("log2(-Infinity)", log2(Double.NEGATIVE_INFINITY));
        // exponential function exp(x)-> 0 for x -> -Infinity. same for exp2 and exp10
        assertEquals("exp10(-Infinity)", 0, exp10(Double.NEGATIVE_INFINITY), 1e-100);
        assertEquals("exp2(-Infinity)", 0, exp2(Double.NEGATIVE_INFINITY), 1e-100);
    }

}
