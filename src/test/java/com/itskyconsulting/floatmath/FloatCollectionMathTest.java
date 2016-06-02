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

    @Test
    public void testMeansWithOneElement() {
        for (int i = -1000; i < 1000; i++) {
            double x = i;
            assertEquals(x, arithmeticMean(x), 1e-100);
            assertEquals(x, geometricMean(x), 1e-100);
            assertEquals(x, harmonicMean(x), 1e-100);
            assertEquals(x, quadraticMean(x), 1e-100);
            assertEquals(x, cubicMean(x), 1e-100);
        }
    }

    @Test
    public void testMeansWithSameElementTwice() {
        for (int i = 0; i < 1000; i++) {
            double x = i;
            double u = Math.ulp(x);
            String str = "i=" + i + " x=" + x + " u=" + u;
            assertEquals(str, x, arithmeticMean(x, x), 1e-100);
            assertEquals(str, x, geometricMean(x, x), 1e-100);
            assertEquals(str, x, harmonicMean(x, x), u);
            assertEquals(str, x, quadraticMean(x, x), u);
            assertEquals(str, x, cubicMean(x, x), u);
        }
    }

    @Test
    public void testMeansWithSameElementThreeTimes() {
        for (int i = 0; i < 1000; i++) {
            double x = i;
            double u = 2*Math.ulp(x);
            String str = "i=" + i + " x=" + x + " u=" + u + " [" + (x-u) + ", " + (x+u) + "]";
            assertEquals(str, x, arithmeticMean(x, x, x), 1e-100);
            assertEquals(str, x, geometricMean(x, x, x), 1e-100);
            assertEquals(str, x, harmonicMean(x, x, x), u);
            assertEquals(str, x, quadraticMean(x, x, x), u);
            assertEquals(str, x, cubicMean(x, x, x), u);
        }
    }

    @Test
    public void testArithmeticMeanOfTwo() {
        for (int i = -100; i < 100; i++) {
            for (int j = - 100; j < 100; j++) {
                double x = 2*i;
                double y = 2*j;
                double z = i+j;
                assertEquals(z, arithmeticMean(x, y), 1e-100);
            }
        }
    }

    @Test
    public void testGeometricMeanOfTwo() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                double x = i*i;
                double y = j*j;
                double z = i*j;
                assertEquals(z, geometricMean(x, y), 1e-100);
            }
        }
    }

    @Test
    public void testHarmonicMeanOfTwoPos() {
        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                double factor = 1;
                checkHarmonicMeanOfTwo(i, j, factor);
                checkHarmonicMeanOfTwo(-i, -j, factor);
            }
        }
    }

    @Test
    public void testHarmonicMeanOfTwo() {
        for (int i = -100; i < 100; i++) {
            if (i == 0) {
                continue;
            }
            for (int j = - 100; j < 100; j++) {
                if (j == 0) {
                    continue;
                }
                if (i > 0 && j > 0) {
                    continue;
                }
                if (i < 0 && j < 0) {
                    continue;
                }
                double factor = 128;
                checkHarmonicMeanOfTwo(i, j, factor);
            }
        }
    }


    private void checkHarmonicMeanOfTwo(int i, int j, double factor) {
        double x = 1.0/(2*i);
        double y = 1.0/(2*j);
        double z = 1.0/(i+j);
        double u = factor*Math.ulp(z);
        double h = harmonicMean(x, y);
        double v = Math.abs(h-z) / Math.ulp(z);
        String str = "i=" + i + " j=" + j + " x=" + x + " y=" + y + " z=" + z + " u=" + u + " [" + (z-u) + ", " + (z+u) + "] v=" + v;
        if (v > 1) {
            System.out.println(str);
        }
        assertEquals(str, z, h, u);
    }

    @Test
    public void testQuadraticMeanOfTwo() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                double x = Math.sqrt(2*i);
                double y = Math.sqrt(2*j);
                double z = Math.sqrt(i+j);
                double u = Math.ulp(z);
                String str = "i=" + i + " j=" + j + " x=" + x + " y=" + y + " z=" + z + " u=" + u;
                assertEquals(str, z, quadraticMean(x, y), u);
            }
        }
    }

    @Test
    public void testCubicMeanOfTwo() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                double x = Math.cbrt(2*i);
                double y = Math.cbrt(2*j);
                double z = Math.cbrt(i+j);
                double u = Math.ulp(z);
                String str = "i=" + i + " j=" + j + " x=" + x + " y=" + y + " z=" + z + " u=" + u;
                assertEquals(str, z, cubicMean(x, y), u);
            }
        }
    }

}
