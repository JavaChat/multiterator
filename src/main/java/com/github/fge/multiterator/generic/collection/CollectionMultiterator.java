package com.github.fge.multiterator.generic.collection;

import com.github.fge.multiterator.Values;
import com.github.fge.multiterator.generic.GenericMultiterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;

public final class CollectionMultiterator<T>
    extends GenericMultiterator<T>
{
    private final Collection<T> collection;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public CollectionMultiterator(final Collection<T> collection,
        final int windowSize, final boolean windowed)
    {
        super(collection.size(), windowSize, windowed);
        this.collection = collection;
    }

    @Override
    public Spliterator<Values<T>> spliterator()
    {
        final Iterator<T> iterator = collection.iterator();
        return new CollectionValuesSpliterator<>(iterator, inputSize,
            windowSize, windowed);
    }
}
