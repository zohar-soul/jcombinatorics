/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 4, 2009
 */
package jcombinatorics.util;

import java.util.Iterator;

/**
 * @param <T>
 *        a type
 * @author Alistair A. Israel
 */
public abstract class ReadOnlyIterator<T> implements Iterator<T> {

    /**
     * Not supported. Throws {@link RemoveNotSupported} exception.
     */
    public final void remove() {
        throw new RemoveNotSupported();
    }

}
