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

import java.util.Iterator;

import jcombinatorics.Generator;
import jcombinatorics.util.ReadOnlyIterator;

/**
 * A combinations generator based on combinadics. Capable of computing the
 * <i>i</i>-th combination directly using {@link #get(long)}.
 *
 * @author Alistair A. Israel
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Combinadic">http://en.wikipedia.org/wiki/Combinadic</a>
 * @see <a
 *      href="http://msdn.microsoft.com/en-us/library/aa289166%28VS.71%29.aspx">Generating
 *      the mth Lexicographical Element of a Mathematical Combination, James
 *      McCaffrey</a>
 */
public class CombinadicCombinationsGenerator2 implements Generator<int[]> {

    private final int n;

    private final int k;

    private final long count;

    /**
     * @param n
     *        the number of elements to choose from
     * @param k
     *        taken <code>k</code> at a time
     */
    public CombinadicCombinationsGenerator2(final int n, final int k) {
        this.n = n;
        this.k = k;
        this.count = Combinations.count(n, k);
    }

    /**
     * <p>
     * Retrieve the <i>l</i>-th combination.
     * </p>
     * <p>
     * The main loop logic equivalent to the following:
     * </p>
     *
     * <pre>
     * int v = n - 1;
     * for (int i = k; i &gt; 0; --i) {
     *     long c = count(v, i);
     *     // in-lined find largest v
     *     while (c &gt; m) {
     *         c = c * (v - i) / v;
     *         --v;
     *     }
     *     m -= c;
     *     a[k - i] = (n - 1) - v;
     * }
     * </pre>
     *
     * @param l
     *        long
     * @return int[]
     */
    public final int[] get(final long l) {
        final int[] a = new int[k];
        long m = count - l - 1;

        int v = n - 1;
        long c = count(v, k);
        for (int i = k; i > 0;) {
            // in-lined find largest v
            while (c > m) {
                c = c * (v - i) / v;
                --v;
            }
            m -= c;
            a[k - i] = (n - 1) - v;
            --i;
            if (v > i) {
                c = c * (i + 1) / (v - i);
            } else {
                c = v;
            }
        }
        return a;
    }

    /**
     * @param n
     *        n
     * @param k
     *        k
     * @return C(n,k)
     */
    private static int count(final int n, final int k) {
        int count = n;
        for (int i = 1; i < k; ++i) {
            count = count * (n - i) / (i + 1);
        }
        return count;
    }

    /**
     * @author Alistair A. Israel
     */
    private class IteratorImpl extends ReadOnlyIterator<int[]> {

        private long i;

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext() {
            return i < count;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#next()
         */
        public int[] next() {
            return get(i++);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Iterable#iterator()
     */
    public final Iterator<int[]> iterator() {
        return new IteratorImpl();
    }

}
