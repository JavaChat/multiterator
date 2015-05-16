package com.github.fge.multiterator.collection;

import com.github.fge.multiterator.base.ValuesIteratorBase;

import java.util.Iterator;

final class CollectionValuesIterator<T>
    extends ValuesIteratorBase<T, CollectionValues<T>>
{
    private final Iterator<T> iterator;

    CollectionValuesIterator(final Iterator<T> iterator,
        final int inputSize, final int windowSize, final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
        this.iterator = iterator;
    }

    @Override
    protected CollectionValues<T> initialValue()
    {
        return new CollectionValues<T>(iterator, inputSize, windowSize);
    }
}
