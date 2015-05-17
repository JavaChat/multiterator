package com.github.fge.multiterator;

import com.github.fge.multiterator.generic.array.ArrayMultiterator;
import com.github.fge.multiterator.generic.collection.CollectionMultiterator;
import com.github.fge.multiterator.ints.asis.IntMultiteratorAsIs;
import com.github.fge.multiterator.ints.fromchars.IntMultiteratorFromChars;
import com.github.fge.multiterator.generic.list.ListMultiterator;

import java.util.Collection;
import java.util.List;

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
        checkSize(collection.size());
        return new CollectionMultiterator<>(collection, windowSize, windowed);
    }

    public <T> Multiterator<T> over(final List<T> list)
    {
        checkSize(list.size());
        return new ListMultiterator<>(list,  windowSize, windowed);
    }

    public <T> Multiterator<T> over(final T[] array)
    {
        checkSize(array.length);
        return new ArrayMultiterator<>(array, windowSize, windowed);
    }

    public IntMultiterator over(final int[] array)
    {
        checkSize(array.length);
        return new IntMultiteratorAsIs(array, windowSize, windowed);
    }

    public IntMultiterator over(final char[] array)
    {
        checkSize(array.length);
        return new IntMultiteratorFromChars(array, windowSize, windowed);
    }

    private void checkSize(final int size)
    {
        if (size < windowSize)
            throw new IllegalArgumentException("size of collection (" + size
                + ") is lower than requested window size (" + windowSize + ')');
        if (windowed && size % windowSize != 0)
            throw new IllegalArgumentException("size of collection (" + size
                + ") is not a multiple of requested window size (" + windowSize
                + ')');
    }
}
