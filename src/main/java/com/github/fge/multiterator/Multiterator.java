package com.github.fge.multiterator;

import java.util.Iterator;
import java.util.stream.Stream;

@FunctionalInterface
public interface Multiterator<T>
    extends Iterable<Values<T>>
{
    static MultiteratorBuilder ofSize(final int windowSize)
    {
        return new MultiteratorBuilder(windowSize);
    }

    Stream<Values<T>> stream();

    @Override
    default Iterator<Values<T>> iterator()
    {
        return stream().iterator();
    }
}
