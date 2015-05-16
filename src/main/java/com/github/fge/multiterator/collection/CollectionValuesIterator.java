package com.github.fge.multiterator.collection;

import com.github.fge.multiterator.base.ValuesIteratorBase;

import java.util.Iterator;
import java.util.function.UnaryOperator;

final class CollectionValuesIterator<T>
    extends ValuesIteratorBase<T, CollectionValues<T>>
{
    private final Iterator<T> iterator;

    CollectionValuesIterator(final Iterator<T> iterator,
        final int inputSize, final int windowSize,
        final UnaryOperator<CollectionValues<T>> operator)
    {
        super(inputSize, windowSize, operator);
        this.iterator = iterator;
    }

    @Override
    protected CollectionValues<T> initialValue()
    {
        return new CollectionValues<T>(iterator, inputSize, windowSize);
    }
}
