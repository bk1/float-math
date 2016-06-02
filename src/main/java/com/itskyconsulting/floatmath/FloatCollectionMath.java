// -*- coding: utf-8-unix -*- Юникод/UTF-8
// (C) Karl Brodowsky / IT Sky Consulting GmbH 2016
// GNU LESSER GENERAL PUBLIC LICENSE, Version 2.1 of February 1999

package com.itskyconsulting.floatmath;

import java.util.Collection;
import java.util.SortedSet;

/**
 * Floating point functions that work on collections or arrays
 *
 */
public class FloatCollectionMath {

    /** min of an array or more than two parameters */
    public static double min(double... d) {
        double result = Double.POSITIVE_INFINITY;
        for (int i = 0; i < d.length; i++) {
            result = Math.min(result, d[i]);
        }
        return result;
    }

    /** max of an array or more than two parameters */
    public static double max(double... d) {
        if (d.length == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        double result = d[0];
        for (int i = 1; i < d.length; i++) {
            result = Math.max(result, d[i]);
        }
        return result;
    }

    /** min of a SortedSet */
    public static <T> T minSorted(SortedSet<T> s, T totalMax) {
        if (s.isEmpty()) {
            return totalMax;
        } else {
            return s.first();
        }
    }

    /** min of a SortedSet */
    public static <T> T minSorted(SortedSet<T> s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException("s must not be empty");
        } else {
            return s.first();
        }
    }

    /** min of a SortedSet */
    public static <T extends Comparable<T>> T min(Collection<T> s) {
        if (s instanceof SortedSet) {
            return minSorted((SortedSet<T>) s);
        }
        T result = minInternal(s);
        if (result == null) {
            throw new IllegalArgumentException("s must not be empty and contain non-null elements");
        } else {
            return result;
        }
    }

    private static <T extends Comparable<T>> T minInternal(Collection<T> s) {
        T result = null;
        for (T element : s) {
            if (element == null) {
                continue;
            }
            if (result == null) {
                result = element;
            } else if (result.compareTo(element) > 0) {
                result = element;
            }
        }
        return result;
    }

    /** min of a SortedSet */
    public static <T extends Comparable<T>> T min(Collection<T> s, T totalMax) {
        if (s instanceof SortedSet) {
            return minSorted((SortedSet<T>) s, totalMax);
        }
        T result = minInternal(s);
        if (result == null) {
            return totalMax;
        } else {
            return result;
        }
    }

    /** max of a SortedSet */
    public static <T> T maxSorted(SortedSet<T> s, T totalMin) {
        if (s.isEmpty()) {
            return totalMin;
        } else {
            return s.last();
        }
    }

    /** max of a SortedSet */
    public static <T> T maxSorted(SortedSet<T> s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException("s must not be empty");
        } else {
            return s.last();
        }
    }

    /** max of a SortedSet */
    public static <T extends Comparable<T>> T max(Collection<T> s) {
        if (s instanceof SortedSet) {
            return maxSorted((SortedSet<T>) s);
        }
        T result = maxInternal(s);
        if (result == null) {
            throw new IllegalArgumentException("s must not be empty and contain non-null elements");
        } else {
            return result;
        }
    }

    private static <T extends Comparable<T>> T maxInternal(Collection<T> s) {
        T result = null;
        for (T element : s) {
            if (element == null) {
                continue;
            }
            if (result == null) {
                result = element;
            } else if (result.compareTo(element) < 0) {
                result = element;
            }
        }
        return result;
    }

    /** max of a SortedSet */
    public static <T extends Comparable<T>> T max(Collection<T> s, T totalMin) {
        if (s instanceof SortedSet) {
            return maxSorted((SortedSet<T>) s, totalMin);
        }
        T result = maxInternal(s);
        if (result == null) {
            return totalMin;
        } else {
            return result;
        }
    }

    public static double sum(double ... arr) {
        double sum = 0.0;
        double correction = 0.0;
        for (double x : arr) {
            if (Double.isInfinite(x)) {
                if (x == -sum) {
                    return Double.NaN;
                } else {
                    sum = x;
                    continue;
                }
            }

            double y = x - correction;
            double t = sum + y;
            correction = (t - sum) - y;
            sum = t;
        }
        return sum;
    }


    /** average (arithmetic mean) */
    public static double arithmeticMean(double ... arr) {
        int n = arr.length;
        if (n == 0) {
            return Double.NaN;
        } else {
            return sum(arr)/n;
        }
    }

    public static double prod(double ... arr) {
        double prod = 1.0;
        for (double x : arr) {
            prod *= x;
        }
        return prod;
    }

    /** geometric mean */
    public static double geometricMean(double ... arr) {
        int n = arr.length;
        if (n == 0) {
            return Double.NaN;
        } else if (n == 1) {
            return arr[0];
        } else {
            double prod = prod(arr);
            if (n == 2) {
                return Math.sqrt(prod);
            } else if (n == 3) {
                return Math.cbrt(prod);
            } else {
                return Math.pow(prod, 1.0/n);
            }
        }
    }

    /** harmonic mean */
    public static double harmonicMean(double ... arr) {
        int n = arr.length;
        if (n == 0) {
            return Double.NaN;
        } else if (n == 1) {
            return arr[0];
        } else {
            double[] recarr = new double[n];
            for (int i = 0; i < n; i++) {
                recarr[i] = 1/arr[i];
            }
            double recmean = arithmeticMean(recarr);
            return 1/recmean;
        }
    }

    /** quadratic mean */
    public static double quadraticMean(double ... arr) {
        int n = arr.length;
        if (n == 0) {
            return Double.NaN;
        } else if (n == 1) {
            return arr[0];
        } else {
            double[] sqrarr = new double[n];
            for (int i = 0; i < n; i++) {
                sqrarr[i] = FloatMathExt.square(arr[i]);
            }
            double sqrmean = arithmeticMean(sqrarr);
            return Math.sqrt(sqrmean);
        }
    }

    /** cubic mean */
    public static double cubicMean(double ... arr) {
        int n = arr.length;
        if (n == 0) {
            return Double.NaN;
        } else if (n == 1) {
            return arr[0];
        } else {
            double[] cubarr = new double[n];
            for (int i = 0; i < n; i++) {
                cubarr[i] = FloatMathExt.cube(arr[i]);
            }
            double cubmean = arithmeticMean(cubarr);
            return Math.cbrt(cubmean);
        }
    }

    
        
        
}
