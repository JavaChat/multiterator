package com.github.fge.multiterator.ints;

import java.util.Iterator;
import java.util.stream.IntStream;

public interface IntValues
    extends Iterable<Integer>
{
    int get(int index);

    default int first()
    {
        return get(0);
    }

    default int second()
    {
        return get(1);
    }

    int size();

    IntStream stream();

    @Override
    default Iterator<Integer> iterator()
    {
        return stream().boxed().iterator();
    }

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    @Override
    String toString();
}
