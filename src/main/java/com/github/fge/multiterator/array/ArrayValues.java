package com.github.fge.multiterator.array;

import com.github.fge.multiterator.Values;

public final class ArrayValues<T>
    implements Values<T>
{
    private final int windowSize;
    private final int arraySize;
    private final int offset;
    private final T[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    ArrayValues(final T[] array, final int arraySize, final int windowSize)
    {
        this.array = array;
        this.arraySize = arraySize;
        this.windowSize = windowSize;
        offset = 0;
    }

    private ArrayValues(final ArrayValues<T> prev, final int offset)
    {
        windowSize = prev.windowSize;
        arraySize = prev.arraySize;
        array = prev.array;
        this.offset = offset;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(final int index)
    {
        if (index >= windowSize)
            throw new IndexOutOfBoundsException();
        return array[offset + index];
    }

    boolean hasNext()
    {
        return offset + windowSize < arraySize;
    }

    ArrayValues<T> shift()
    {
        return new ArrayValues<>(this, offset + 1);
    }

    ArrayValues<T> nextWindow()
    {
        return new ArrayValues<>(this, offset + windowSize);
    }
}
