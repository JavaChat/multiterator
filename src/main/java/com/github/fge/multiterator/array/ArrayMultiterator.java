package com.github.fge.multiterator.array;

import com.github.fge.multiterator.Multiterator;
import com.github.fge.multiterator.Values;

import java.util.Iterator;
import java.util.function.UnaryOperator;

public final class ArrayMultiterator<T>
    implements Multiterator<T>
{
    private final T[] array;
    private final int size;
    private final int windowSize;
    private final UnaryOperator<ArrayValues<T>> operator;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public ArrayMultiterator(final T[] array, final int windowSize,
        final boolean windowed)
    {
        this.array = array;
        size = array.length;
        this.windowSize = windowSize;
        operator = windowed ? ArrayValues::nextWindow : ArrayValues::shift;
    }

    @Override
    public Iterator<Values<T>> iterator()
    {
        return new ArrayValuesIterator<>(array, size, windowSize, operator);
    }
}
