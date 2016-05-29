package com.itskyconsulting.floatmath;

/**
 * Floating point functions that we would expect in the standard library
 *
 */
public class FloatMathExt {

    /** pi/2.  This is used so often that it should not be recalculated each time */
    public static final double HALF_PI = Math.PI/2;

    public static final double DEG_RAD_FACTOR = Math.PI / 180;
    
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


    /** sin with degrees */
    public static double sind(double x) {
        return Math.sin(x * DEG_RAD_FACTOR);
    }
    
    /** cos with degrees */
    public static double cosd(double x) {
        return Math.cos(x * DEG_RAD_FACTOR);
    }
    
    /** tan with degrees */
    public static double tand(double x) {
        return Math.tan(x * DEG_RAD_FACTOR);
    }

    /** cot with degrees */
    public static double cotd(double x) {
        return tand(90 - x);
    }
    
    /** sec with degrees */
    public static double secd(double x) {
        return sec(x * DEG_RAD_FACTOR);
    }
    
    /** csc with degrees */
    public static double cscd(double x) {
        return csc(x * DEG_RAD_FACTOR);
    }


    /** asin with degrees */
    public static double asind(double x) {
        return Math.asin(x) / DEG_RAD_FACTOR;
    }
    
    /** acos with degrees */
    public static double acosd(double x) {
        return Math.acos(x) / DEG_RAD_FACTOR;
    }
    
    /** atan with degrees */
    public static double atand(double x) {
        return Math.atan(x) / DEG_RAD_FACTOR;
    }

    /** acot with degrees */
    public static double acotd(double x) {
        return 90 - atand(x);
    }
    
    /** asec with degrees */
    public static double asecd(double x) {
        return asec(x) / DEG_RAD_FACTOR;
    }
    
    /** acsc with degrees */
    public static double acscd(double x) {
        return acsc(x) / DEG_RAD_FACTOR;
    }
    
    @Deprecated
    public static double sinh(double x) {
    	return Math.sinh(x);
    }

    @Deprecated
    public static double cosh(double x) {
    	return Math.cosh(x);
    }

    @Deprecated
    public static double tanh(double x) {
    	return Math.tanh(x);
    }

    /**
     *  tanh(x) = sinh(x)/cosh(x) which is zero if and only if x=0
     *  coth(x) = cosh(x)/sinh(x) which is defined for all x != 0
     *  So we can use coth(x) = 1/tanh(x)
     * @param x
     * @return
     */
    public static double coth(double x) {
    	return 1/Math.tanh(x);
    }

    /**
     *  sech(x) = 1/cosh(x) which is defined for all x
     * @param x
     * @return
     */
    public static double sech(double x) {
    	return 1/Math.cosh(x);
    }

    /**
     *  csch(x) = 1/sinh(x) which is defined for all x != 0
     * @param x
     * @return
     */
    public static double csch(double x) {
    	return 1/Math.sinh(x);
    }
    
    /**
     * calculate area sine (inverse of sinh)
     * defined for all x
     * 
     * sinh(x) = (exp(x) - exp(-x))/2
     * let z = exp(x)
     * let s = sinh(x)
     * calculate first z, then x
     * s = (z - 1/z)/2
     * 2*s*z = z*z - 1
     * z^2 - 2*z*s - 1 = 0
     * z = s +/- sqrt(s^2 + 1)
     * since the choice with - would yield a negative number, which cannot be exp(x) for any real x, we have to go with the+
     * z = s + sqrt(s^2 + 1)
     * x = log(s + sqrt(s^2 + 1))
     * @param s
     * @return asinh(s)
     */
    public static double asinh(double s) {
    	double z = s + Math.sqrt(s*s + 1);
    	return Math.log(z);
    }
    
    /**
     * calculate area cosine (inverse of cosh)
     * defined for x >= 1
     * 
     * cosh(x) = (exp(x) + exp(-x))/2
     * let z = exp(x)
     * let c = cosh(x)
     * calculate first z, then x
     * c = (z + 1/z)/2
     * z^2 - 2*z*c + 1 = 0
     * z = c +/- sqrt(c^2 - 1)
     * so
     * z1 = c + sqrt(c^2 - 1)
     * z2 = c - sqrt(c^2 - 1)
     * both choices are possible and by the fact that cosh is an even function, satifying cosh(-x)= cosh(x) we can conclude
     * that z1 = 1/z2. (or by multiplying z1 and z2) 
     * Since we prefer the positive of the two possible results, we have to pick the larger number, which is z1.
     * x = log(z1) = log(c + sqrt(c^2 - 1))
     * @param c
     * @return acosh(c)
     */
    public static double acosh(double c) {
        if (c < 1) {
            return Double.NaN;
        }
    	double z = c + Math.sqrt(c*c - 1);
    	return Math.log(z);
    }
    
    /**
     * calculate area tangent (inverse of tanh)
     * defined for -1 < x < 1
     * 
     * tanh(x) = sinh(x)/cosh(x) = (exp(x) - exp(-x))/(exp(x) + exp(-x))
     *
     * So we can already assume that -1 < tanh(x) < 1
     *
     * let z = exp(x)
     * let t = tanh(x)
     * calculate first z, then x
     * t = (z - 1/z)/(z + 1/z) = (z^2-1)/(z^2+1)
     * t*(z^2+1) = z^2-1
     * (1-t)z^2 = t+1
     * z^2 = (1+t)/(1-t)
     * z = +/- sqrt((1+t)/(1-t))
     * The negative solution is not possible for real x.
     * x = log(z) = log(sqrt((1+t)/(1-t))) = log((1+t)/(1-t))/2
     * @param t
     * @return atanh(t)
     */
    public static double atanh(double t) {
        if (t <= -1.0 || t >= 1.0) {
            return Double.NaN;
        }
    	double z = (1+t)/(1-t);
    	return Math.log(z) / 2;
    }
    
    /**
     * calculate area cotangent (inverse of coth)
     * defined for x < -1 or x > 1
     * 
     * coth(x) = cosh(x)/sinh(x) = (exp(x) + exp(-x))/(exp(x) - exp(-x))
     *
     * So we can already assume that coth(x) < -1 or coth(x) > 1
     *
     * let z = exp(x)
     * let t = coth(x)
     * calculate first z, then x
     * t = (z + 1/z)/(z - 1/z) = (z^2+1)/(z^2-1)
     * t*(z^2-1) = z^2+1
     * (t-1)z^2 = 1+t
     * z^2 = (t+1)/(t-1)
     * since |t| > 1 we can be sure that (t-1)/(t+1) > 0
     * z = +/- sqrt((t+1)/(t-1))
     * The negative solution is not possible for real x.
     * x = log(z) = log(sqrt((t+1)/(t-1))) = log((t+1)/(t-1))/2
     * @param t
     * @return acoth(t)
     */
    public static double acoth(double t) {
        if (-1 <= t && t <= 1) {
            return Double.NaN;
        }
    	double z = (t+1)/(t-1);
    	return Math.log(z) / 2;
    }

    /**
     *  calculate the area secant (inverse of sech)
     *  defined for 0 < s < 1
     *  @param s
     *  @return asech(s)
     */
    public static double asech(double s) {
        if (s <= 0 || s > 1) {
            return Double.NaN;
        }
        return acosh(1/s);
    }

    /**
     *  calculate the area secant (inverse of sech)
     *  defined for s != 0
     *  @param s
     *  @return asech(s)
     */
    public static double acsch(double s) {
        if (s == 0) {
            return Double.NaN;
        }
        return asinh(1/s);
    }
        
}
