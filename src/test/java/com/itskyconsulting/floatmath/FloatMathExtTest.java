package com.itskyconsulting.floatmath;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.itskyconsulting.floatmath.FloatMathExt.*;

/**
 * Unit test for simple App.
 */
public class FloatMathExtTest {

    /** helper function: make sure that a function that would be +/- infinity mathematically is actually at least NaN, +/-Infinity or large by absolute value */
    private void assertInfinite(String s, double d, double r)  {
        if (Double.isInfinite(d)) {
            return;
        }
        if (Double.isNaN(d)) {
            return;
        }
        assertTrue(s, Math.abs(d) > r);
    }

    /** helper function: make sure that a function that would be +/- infinity mathematically is actually at least NaN, +/-Infinity or large by absolute value */
    private void assertNaN(String s, double d) {
        assertTrue(s + " d=" + d, Double.isNaN(d));
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
        for (int i = -3; i <= 3; i++) {
            double x = i;
            assertEquals("i=" + i + " x=" + x, x, Math.sinh(asinh(x)), 1e-14);
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
        for (int i = -9; i <= 9; i++) {
            double x = i / 10.0;
            double a = atanh(x);
            assertEquals("i=" + i + " x=" + x + " a=" + a, x, Math.tanh(a), 1e-14);
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
            System.out.println("x=" + x + " s=" + s);
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

}
