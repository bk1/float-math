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
}
