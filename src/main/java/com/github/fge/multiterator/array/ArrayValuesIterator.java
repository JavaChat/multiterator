package com.github.fge.multiterator.array;

import com.github.fge.multiterator.base.ValuesIteratorBase;

final class ArrayValuesIterator<T>
    extends ValuesIteratorBase<T, ArrayValues<T>>
{
    private final T[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    ArrayValuesIterator(final T[] array, final int inputSize,
        final int windowSize, final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
        this.array = array;
    }

    @Override
    protected ArrayValues<T> initialValue()
    {
        return new ArrayValues<>(array, inputSize, windowSize);
    }
}
