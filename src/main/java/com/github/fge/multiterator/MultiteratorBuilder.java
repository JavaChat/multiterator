package com.github.fge.multiterator;

import com.github.fge.multiterator.collection.CollectionMultiterator;

import java.util.Collection;

public final class MultiteratorBuilder
{
    private final int windowSize;
    private boolean windowed = false;

    MultiteratorBuilder(final int windowSize)
    {
        if (windowSize <= 0)
            throw new IllegalArgumentException("window size must be strictly"
                + " positive");
        this.windowSize = windowSize;
    }

    public MultiteratorBuilder windowed()
    {
        windowed = true;
        return this;
    }

    public <T> Multiterator<T> over(final Collection<T> collection)
    {
        final int size = collection.size();
        if (size < windowSize)
            throw new IllegalArgumentException("size of collection (" + size
                + ") is lower than requested window size (" + windowSize + ')');
        if (windowed && size % windowSize != 0)
            throw new IllegalArgumentException("size of collection (" + size
                + ") is not a multiple of requested window size (" + windowSize
                + ')');
        return new CollectionMultiterator<>(collection, windowSize, windowed);
    }
}
