package com.github.fge.multiterator.generic.collection;

import com.github.fge.multiterator.generic.GenericValuesSpliterator;

import java.util.Iterator;

final class CollectionValuesSpliterator<T>
    extends GenericValuesSpliterator<T, CollectionValues<T>>
{
    private final Iterator<T> iterator;

    CollectionValuesSpliterator(final Iterator<T> iterator, final int inputSize,
        final int windowSize, final boolean windowed)
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
