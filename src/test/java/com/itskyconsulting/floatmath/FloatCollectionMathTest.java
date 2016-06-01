package com.itskyconsulting.floatmath;

import static org.junit.Assert.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import static com.itskyconsulting.floatmath.FloatCollectionMath.*;

public class FloatCollectionMathTest {

    private static final Double ZERO = Double.valueOf(0.0);

    @Test
    public void testMinEmptyArr() {
        assertEquals(Double.POSITIVE_INFINITY, min(), 0);
    }

    @Test
    public void testMinSingleArr() {
        for (int i = -10; i < 10; i++) {
            double d = i / 10.0;
            assertEquals(d, min(d), 0);
        }
    }

    @Test
    public void testMinDoubleIncreasingArr() {
        for (int i = -10; i < 10; i++) {
            double di = i / 10.0;
            for (int j = i; j < 10; j++) {
                double dj = j / 10.0;
                assertEquals(di, min(di, dj), 0);
            }
        }
    }

    @Test
    public void testMinDoubleDecreasingArr() {
        for (int i = -10; i < 10; i++) {
            double di = i / 10.0;
            for (int j = -10; j <= i; j++) {
                double dj = j / 10.0;
                assertEquals(dj, min(di, dj), 0);
            }
        }
    }

    @Test
    public void testMinTripleArr() {
        for (int i = -10; i < 10; i++) {
            double di = i / 10.0;
            for (int j = i; j <= 10; j++) {
                double dj = j / 10.0;
                for (int k = i; k <= 10; k++) {
                    double dk = k / 10.0;
                    assertEquals(di, min(di, dj, dk), 0);
                    assertEquals(di, min(dj, di, dk), 0);
                    assertEquals(di, min(dj, dk, di), 0);
                }
            }
        }
    }

    @Test
    public void testMaxEmptyArr() {
        assertEquals(Double.NEGATIVE_INFINITY, max(), 0);
    }

    @Test
    public void testMaxSingleArr() {
        for (int i = -10; i < 10; i++) {
            double d = i / 10.0;
            assertEquals(d, max(d), 0);
        }
    }

    @Test
    public void testMaxDoubleIncreasingArr() {
        for (int i = -10; i < 10; i++) {
            double di = i / 10.0;
            for (int j = i; j < 10; j++) {
                double dj = j / 10.0;
                assertEquals(dj, max(di, dj), 0);
            }
        }
    }

    @Test
    public void testMaxDoubleDecreasingArr() {
        for (int i = -10; i < 10; i++) {
            double di = i / 10.0;
            for (int j = -10; j <= i; j++) {
                double dj = j / 10.0;
                assertEquals(di, max(di, dj), 0);
            }
        }
    }

    @Test
    public void testMaxTripleArr() {
        for (int i = -10; i < 10; i++) {
            double di = i / 10.0;
            for (int j = -10; j <= i; j++) {
                double dj = j / 10.0;
                for (int k = -10; k <= i; k++) {
                    double dk = k / 10.0;
                    assertEquals(di, max(di, dj, dk), 0);
                    assertEquals(di, max(dj, di, dk), 0);
                    assertEquals(di, max(dj, dk, di), 0);
                }
            }
        }
    }

    @Test
    public void testMinEmptyCollection() {
        Collection<Double> empty = Collections.emptyList();
        assertEquals(Double.valueOf(Double.POSITIVE_INFINITY),
                     min(empty, Double.valueOf(Double.POSITIVE_INFINITY)));
    }


    @Test
    public void testMinSingleCollection() {
        for (int i = -10; i < 10; i++) {
            double d = i / 10.0;
            List<Double> listD = Arrays.asList(d);
            assertEquals(d, min(listD).doubleValue(), 0);
            HashSet<Double> hashSet = new HashSet<Double>(listD);
            assertEquals(d, min(hashSet).doubleValue(), 0);
            TreeSet<Double> treeSet = new TreeSet<Double>(listD);
            assertEquals(d, min(treeSet).doubleValue(), 0);
            assertEquals(d, min(listD, ZERO).doubleValue(), 0);
            assertEquals(d, min(hashSet,ZERO).doubleValue(), 0);
            assertEquals(d, min(treeSet, ZERO).doubleValue(), 0);
        }
    }

    @Test
    public void testMinDoubleCollection() {
        for (int i = -10; i < 10; i++) {
            double di = -10 / 10.0;
            for (int j = i; j < 10; j++) {
                double dj = j / 10.0;
                double dm = min(di, dj);
                List<Double> list = Arrays.asList(di, dj);
                HashSet<Double> hashSet = new HashSet<Double>(list);
                TreeSet<Double> treeSet = new TreeSet<Double>(list);
                assertEquals(dm, min(list).doubleValue(), 0);
                assertEquals(dm, min(hashSet).doubleValue(), 0);
                assertEquals(dm, min(treeSet).doubleValue(), 0);
                assertEquals(dm, min(list, ZERO).doubleValue(), 0);
                assertEquals(dm, min(hashSet,ZERO).doubleValue(), 0);
                assertEquals(dm, min(treeSet, ZERO).doubleValue(), 0);
            }
        }
    }

    @Test
    public void testMinTripleColl() {
        for (int i = -10; i < 10; i++) {
            double di = i / 10.0;
            for (int j = -10; j <= 10; j++) {
                double dj = j / 10.0;
                for (int k = -10; k <= 10; k++) {
                    double dk = k / 10.0;
                    double dm = min(di, dj, dk);
                    List<Double> list = Arrays.asList(di, dj, dk);
                    HashSet<Double> hashSet = new HashSet<Double>(list);
                    TreeSet<Double> treeSet = new TreeSet<Double>(list);
                    assertEquals(dm, min(list).doubleValue(), 0);
                    assertEquals(dm, min(hashSet).doubleValue(), 0);
                    assertEquals(dm, min(treeSet).doubleValue(), 0);
                    assertEquals(dm, min(list, ZERO).doubleValue(), 0);
                    assertEquals(dm, min(hashSet,ZERO).doubleValue(), 0);
                    assertEquals(dm, min(treeSet, ZERO).doubleValue(), 0);
                }
            }
        }
    }

    @Test
    public void testMaxEmptyCollection() {
        Collection<Double> empty = Collections.emptyList();
        assertEquals(Double.valueOf(Double.NEGATIVE_INFINITY),
                     max(empty, Double.valueOf(Double.NEGATIVE_INFINITY)));
    }


    @Test
    public void testMaxSingleCollection() {
        for (int i = -10; i < 10; i++) {
            double d = i / 10.0;
            List<Double> listD = Arrays.asList(d);
            assertEquals(d, max(listD).doubleValue(), 0);
            HashSet<Double> hashSet = new HashSet<Double>(listD);
            assertEquals(d, max(hashSet).doubleValue(), 0);
            TreeSet<Double> treeSet = new TreeSet<Double>(listD);
            assertEquals(d, max(treeSet).doubleValue(), 0);
            assertEquals(d, max(listD, ZERO).doubleValue(), 0);
            assertEquals(d, max(hashSet,ZERO).doubleValue(), 0);
            assertEquals(d, max(treeSet, ZERO).doubleValue(), 0);
        }
    }

    @Test
    public void testMaxDoubleCollection() {
        for (int i = -10; i < 10; i++) {
            double di = -10 / 10.0;
            for (int j = i; j < 10; j++) {
                double dj = j / 10.0;
                double dm = max(di, dj);
                List<Double> list = Arrays.asList(di, dj);
                HashSet<Double> hashSet = new HashSet<Double>(list);
                TreeSet<Double> treeSet = new TreeSet<Double>(list);
                assertEquals("di=" + di + " dj=" + dj + " dm=" + dm, dm, max(list).doubleValue(), 0);
                assertEquals(dm, max(hashSet).doubleValue(), 0);
                assertEquals(dm, max(treeSet).doubleValue(), 0);
                assertEquals(dm, max(list, ZERO).doubleValue(), 0);
                assertEquals(dm, max(hashSet,ZERO).doubleValue(), 0);
                assertEquals(dm, max(treeSet, ZERO).doubleValue(), 0);
            }
        }
    }

    @Test
    public void testMaxTripleColl() {
        for (int i = -10; i < 10; i++) {
            double di = i / 10.0;
            for (int j = -10; j <= 10; j++) {
                double dj = j / 10.0;
                for (int k = -10; k <= 10; k++) {
                    double dk = k / 10.0;
                    double dm = max(di, dj, dk);
                    List<Double> list = Arrays.asList(di, dj, dk);
                    HashSet<Double> hashSet = new HashSet<Double>(list);
                    TreeSet<Double> treeSet = new TreeSet<Double>(list);
                    assertEquals(dm, max(list).doubleValue(), 0);
                    assertEquals(dm, max(hashSet).doubleValue(), 0);
                    assertEquals(dm, max(treeSet).doubleValue(), 0);
                    assertEquals(dm, max(list, ZERO).doubleValue(), 0);
                    assertEquals(dm, max(hashSet,ZERO).doubleValue(), 0);
                    assertEquals(dm, max(treeSet, ZERO).doubleValue(), 0);
                }
            }
        }
    }

}
