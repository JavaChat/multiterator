package com.github.fge.multiterator.array;

import com.github.fge.multiterator.Values;
import com.github.fge.multiterator.base.MultiteratorBase;

import java.util.Iterator;

public final class ArrayMultiterator<T>
    extends MultiteratorBase<T, ArrayValues<T>>
{
    private final T[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public ArrayMultiterator(final T[] array, final int windowSize,
        final boolean windowed)
    {
        super(array.length, windowSize, windowed);
        this.array = array;
    }

    @Override
    public Iterator<Values<T>> iterator()
    {
        return new ArrayValuesIterator<>(array, inputSize, windowSize,
            windowed);
    }
}
