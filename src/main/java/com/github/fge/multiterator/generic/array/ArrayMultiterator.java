package com.github.fge.multiterator.generic.array;

import com.github.fge.multiterator.Values;
import com.github.fge.multiterator.generic.GenericMultiterator;

import java.util.Spliterator;

public final class ArrayMultiterator<T>
    extends GenericMultiterator<T>
{
    private final T[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public ArrayMultiterator(final T[] array, final int windowSize,
        final boolean windowed)
    {
        super(array.length, windowSize, windowed);
        this.array = array;
    }

    @Override
    public Spliterator<Values<T>> spliterator()
    {
        return new ArrayValuesSpliterator<>(array, inputSize, windowSize,
            windowed);
    }
}
