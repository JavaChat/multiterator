package com.github.fge.multiterator;

@FunctionalInterface
public interface Multiterator<T>
    extends Iterable<Values<T>>
{
    static MultiteratorBuilder ofSize(final int windowSize)
    {
        return new MultiteratorBuilder(windowSize);
    }
}
