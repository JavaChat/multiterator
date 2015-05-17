package com.github.fge.multiterator.array;

import com.github.fge.multiterator.base.ValuesBase;
import com.github.fge.multiterator.internal.VisibleForTesting;

import java.util.Arrays;
import java.util.stream.Stream;

public final class ArrayValues<T>
    extends ValuesBase<T, ArrayValues<T>>
{
    private final T[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    @VisibleForTesting
    public ArrayValues(final T[] array, final int inputSize,
        final int windowSize)
    {
        super(inputSize, windowSize);
        this.array = array;
    }

    private ArrayValues(final ArrayValues<T> prev, final int offset)
    {
        super(prev, offset);
        array = prev.array;
    }

    @Override
    public T doGet(final int index)
    {
        return array[offset + index];
    }

    @Override
    public ArrayValues<T> shift()
    {
        return new ArrayValues<>(this, offset + 1);
    }

    @Override
    public ArrayValues<T> nextWindow()
    {
        return new ArrayValues<>(this, offset + windowSize);
    }

    @Override
    public Stream<T> stream()
    {
        final T[] subArray
            = Arrays.copyOfRange(array, offset, offset + windowSize);
        return Arrays.stream(subArray);
    }
}
