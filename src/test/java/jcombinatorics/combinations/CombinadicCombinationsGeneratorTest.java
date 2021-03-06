/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 2, 2009
 */
package jcombinatorics.combinations;

import static java.lang.String.format;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit test for {@link CombinadicCombinationsGenerator}.
 *
 * @author Alistair A. Israel
 */
public final class CombinadicCombinationsGeneratorTest {

    private static final int[][] C_5_3 = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 1, 4 }, { 0, 2, 3 }, { 0, 2, 4 },
            { 0, 3, 4 }, { 1, 2, 3 }, { 1, 2, 4 }, { 1, 3, 4 }, { 2, 3, 4 } };

    /**
     *
     */
    @Test
    public void testGenerateC53() {
        final CombinadicCombinationsGenerator generator = new CombinadicCombinationsGenerator(5, 3);
        for (int i = 0; i < C_5_3.length; ++i) {
            final int[] actual = generator.get(i);
            assertArrayEquals(format("C(%d,%d)[%d] expected %s, got %s", 5, 3, i,
                    Arrays.toString(C_5_3[i]), Arrays.toString(actual)), C_5_3[i], actual);
        }
    }

    /**
     * Test for {@link CombinadicCombinationsGenerator#get(long)} .
     */
    @Test
    public void testGet() {
        final int[] n = { 6, 10 };
        final int[] k = { 3, 5 };
        final int[] l = { 17, 72 };
        final int[][] combinations = { { 2, 3, 5 }, { 0, 2, 4, 5, 7 } };
        for (int i = 0; i < combinations.length; ++i) {
            final CombinadicCombinationsGenerator generator = new CombinadicCombinationsGenerator(n[i],
                    k[i]);
            final int[] actual = generator.get(l[i]);
            assertArrayEquals(format("C(%d,%d)[%d] expected %s, got %s", n[i], k[i], l[i], Arrays
                    .toString(combinations[i]), Arrays.toString(actual)), combinations[i], actual);
        }
    }

    /**
     *
     */
    @Test
    public void testCompareWithRosenGenerator() {
        for (int n = 1; n < 10; ++n) {
            for (int k = 0; k < n; ++k) {
                final CombinadicCombinationsGenerator gen = new CombinadicCombinationsGenerator(n, k);
                int l = 0;
                for (final int[] expected : Combinations.choose(n, k)) {
                    final int[] actual = gen.get(l);
                    assertArrayEquals(format("C(%d,%d)[%d] expected %s, got %s", n, k, l, Arrays
                            .toString(expected), Arrays.toString(actual)), expected, actual);
                    ++l;
                }
            }
        }
    }

}
