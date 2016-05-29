package com.itskyconsulting.floatmath;

/**
 * Floating point functions that we would expect in the standard library
 *
 */
public class FloatMathExt {

    /** pi/2.  This is used so often that it should not be recalculated each time */
    public static final double HALF_PI = Math.PI/2;

    /** @deprecated, use Math.sin */
    public static double sin(double x) {
        return Math.sin(x);
    }
    
    /** @deprecated, use Math.cos */
    public static double cos(double x) {
        return Math.cos(x);
    }
    
    /** @deprecated, use Math.tan */
    public static double tan(double x) {
        return Math.tan(x);
    }
    
    /** cot(x) = cos(x)/sin(x). This is often calculated using 1/tan(x), but that does not work for all x. This implementation works for all x for which cot should be defined. */
    public static double cot(double x) {
        return Math.tan(HALF_PI - x);
    }
    
    /** @deprecated, use Math.sec */
    public static double sec(double x) {
        return 1/Math.cos(x);
    }
    
    /** @deprecated, use Math.csc */
    public static double csc(double x) {
        return 1/Math.sin(x);
    }

    /** @deprecated, use Math.asin */
    public static double asin(double x) {
        return Math.asin(x);
    }
    
    /** @deprecated, use Math.acos */
    public static double acos(double x) {
        return Math.acos(x);
    }
    
    /** @deprecated, use Math.atan */
    public static double atan(double x) {
        return Math.atan(x);
    }
    
    public static double acot(double x) {
        return HALF_PI - Math.atan(x);
    }
    
    public static double asec(double x) {
        return Math.acos(1/x);
    }
    
    /** @deprecated, use Math.csc */
    public static double acsc(double x) {
        return Math.asin(1/x);
    }
    
}
