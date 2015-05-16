package com.github.fge.multiterator.array;

import com.github.fge.multiterator.base.ValuesSpliteratorBase;

public final class ArrayValuesSpliterator<T>
    extends ValuesSpliteratorBase<T, ArrayValues<T>>
{
    private final T[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public ArrayValuesSpliterator(final T[] array, final int inputSize,
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
