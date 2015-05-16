package com.github.fge.multiterator.collection;

import com.github.fge.multiterator.Values;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.UnaryOperator;

final class CollectionValuesIterator<T>
    implements Iterator<Values<T>>
{
    private final Iterator<T> iterator;
    private final int collectionSize;
    private final int windowSize;
    private final UnaryOperator<CollectionValues<T>> operator;

    private CollectionValues<T> currentValue;

    CollectionValuesIterator(final Iterator<T> iterator,
        final int collectionSize, final int windowSize,
        final UnaryOperator<CollectionValues<T>> operator)
    {
        this.iterator = iterator;
        this.collectionSize = collectionSize;
        this.windowSize = windowSize;
        this.operator = operator;
    }

    @Override
    public boolean hasNext()
    {
        return currentValue == null || currentValue.hasNext();
    }

    @Override
    public Values<T> next()
    {
        if (!hasNext())
            throw new NoSuchElementException();
        currentValue = currentValue == null
            ? new CollectionValues<>(iterator, collectionSize, windowSize)
            : operator.apply(currentValue);
        return currentValue;
    }
}
