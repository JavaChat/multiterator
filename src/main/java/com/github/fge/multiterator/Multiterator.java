package com.github.fge.multiterator;

import java.util.stream.Stream;

public interface Multiterator<T>
    extends Iterable<Values<T>>
{
    static MultiteratorBuilder ofSize(final int windowSize)
    {
        return new MultiteratorBuilder(windowSize);
    }

    Stream<Values<T>> stream();
}
