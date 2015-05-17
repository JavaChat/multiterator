package com.github.fge.multiterator.primitives.ints.direct;

import com.github.fge.multiterator.primitives.ints.IntValuesSpliteratorBase;

public final class DirectIntValuesSpliterator
    extends IntValuesSpliteratorBase<DirectIntValues>
{
    private final int[] array;

    DirectIntValuesSpliterator(final int[] array, final int inputSize,
        final int windowSize, final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
        this.array = array;
    }

    @Override
    protected DirectIntValues initialValue()
    {
        return new DirectIntValues(array, inputSize, windowSize);
    }
}
