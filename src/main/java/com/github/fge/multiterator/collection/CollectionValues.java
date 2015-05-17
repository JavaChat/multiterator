package com.github.fge.multiterator.collection;

import com.github.fge.multiterator.base.ValuesBase;
import com.github.fge.multiterator.internal.VisibleForTesting;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class CollectionValues<T>
    extends ValuesBase<T, CollectionValues<T>>
{
    private final Object[] values;

    private final Iterator<T> iterator;

    @VisibleForTesting
    public CollectionValues(final Iterator<T> iterator, final int inputSize,
        final int windowSize)
    {
        super(inputSize, windowSize);
        this.iterator = iterator;
        values = new Object[windowSize];

        IntStream.range(0, windowSize)
            .forEach(index -> values[index] = iterator.next());
    }

    private CollectionValues(final CollectionValues<T> prev, final int offset,
        final Object... values)
    {
        super(prev, offset);
        this.values = values;
        iterator = prev.iterator;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T doGet(final int index)
    {
        return (T) values[index];
    }

    @Override
    public CollectionValues<T> shift()
    {
        final Object[] newValues = new Object[windowSize];
        System.arraycopy(values, 1, newValues, 0, windowSize - 1);
        newValues[windowSize - 1] = iterator.next();
        return new CollectionValues<>(this, offset + 1, newValues);
    }

    @Override
    public CollectionValues<T> nextWindow()
    {
        final Object[] newValues = new Object[windowSize];
        IntStream.range(0, windowSize)
            .forEach(index -> newValues[index] = iterator.next());
        return new CollectionValues<>(this, offset + windowSize, newValues);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Stream<T> stream()
    {
        return Arrays.stream((T[]) values);
    }
}
