package com.github.fge.multiterator;

import java.util.Iterator;
import java.util.stream.Stream;

public interface Values<T>
    extends Iterable<T>
{
    T get(int index);

    default T first()
    {
        return get(0);
    }

    default T second()
    {
        return get(1);
    }

    int size();

    Stream<T> stream();

    @Override
    default Iterator<T> iterator()
    {
        return stream().iterator();
    }

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    @Override
    String toString();
}
