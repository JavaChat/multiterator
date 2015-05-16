package com.github.fge.multiterator.collection;

import com.github.fge.multiterator.Multiterator;
import com.github.fge.multiterator.Values;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.UnaryOperator;

public final class CollectionMultiterator<T>
    implements Multiterator<T>
{
    private final Collection<T> collection;
    private final int size;
    private final int windowSize;
    private final UnaryOperator<CollectionValues<T>> operator;

    public CollectionMultiterator(final Collection<T> collection,
        final int windowSize, final boolean windowed)
    {
        this.collection = collection;
        size = collection.size();
        this.windowSize = windowSize;
        operator = windowed ? CollectionValues::nextWindow
            : CollectionValues::shift;
    }

    @Override
    public Iterator<Values<T>> iterator()
    {
        final Iterator<T> iterator = collection.iterator();
        return new CollectionValuesIterator<>(iterator, size, windowSize,
            operator);
    }
}
