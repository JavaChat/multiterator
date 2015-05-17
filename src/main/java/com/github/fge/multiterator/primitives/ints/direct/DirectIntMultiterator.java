package com.github.fge.multiterator.primitives.ints.direct;

import com.github.fge.multiterator.primitives.ints.IntMultiteratorBase;
import com.github.fge.multiterator.IntValues;

import java.util.Spliterator;

public final class DirectIntMultiterator
    extends IntMultiteratorBase
{
    private final int[] array;

    public DirectIntMultiterator(final int[] array, final int windowSize,
        final boolean windowed)
    {
        super(array.length, windowSize, windowed);
        this.array = array;
    }

    @Override
    public Spliterator<IntValues> spliterator()
    {
        return new DirectIntValuesSpliterator(array, inputSize, windowSize,
            windowed);
    }
}
