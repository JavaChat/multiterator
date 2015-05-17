package com.github.fge.multiterator.primitives.doubles.direct;

import com.github.fge.multiterator.primitives.doubles.DoubleValuesSpliteratorBase;

public final class DirectDoubleValuesSpliterator
    extends DoubleValuesSpliteratorBase<DirectDoubleValues>
{
    private final double[] array;

    DirectDoubleValuesSpliterator(final double[] array, final int inputSize,
        final int windowSize, final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
        this.array = array;
    }

    @Override
    protected DirectDoubleValues initialValue()
    {
        return new DirectDoubleValues(array, inputSize, windowSize);
    }
}
