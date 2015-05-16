package com.github.fge.multiterator.collection;

import com.github.fge.multiterator.Values;

import java.util.Iterator;
import java.util.stream.IntStream;

public final class CollectionValues<T>
    implements Values<T>
{
    private final int windowSize;
    private final int collectionSize;
    private final int offset;
    private final Object[] values;

    private final Iterator<T> iterator;

    CollectionValues(final Iterator<T> iterator, final int collectionSize,
        final int windowSize)
    {
        this.iterator = iterator;
        this.collectionSize = collectionSize;
        this.windowSize = windowSize;
        offset = 0;
        values = new Object[windowSize];

        IntStream.range(0, windowSize)
            .forEach(index -> values[index] = iterator.next());
    }

    private CollectionValues(final CollectionValues<T> prev, final int offset,
        final Object[] values)
    {
        windowSize = prev.windowSize;
        collectionSize = prev.collectionSize;
        this.offset = offset;
        this.values = values;
        iterator = prev.iterator;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(final int index)
    {
        return (T) values[index];
    }

    boolean hasNext()
    {
        return offset + windowSize < collectionSize;
    }

    CollectionValues<T> shift()
    {
        final Object[] newValues = new Object[windowSize];
        System.arraycopy(values, 1, newValues, 0, windowSize - 1);
        newValues[windowSize - 1] = iterator.next();
        return new CollectionValues<>(this, offset + 1, newValues);
    }

    CollectionValues<T> nextWindow()
    {
        final Object[] newValues = new Object[windowSize];
        IntStream.range(0, windowSize)
            .forEach(index -> newValues[index] = iterator.next());
        return new CollectionValues<>(this, offset + windowSize, newValues);
    }
}
