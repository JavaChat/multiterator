package com.github.fge.multiterator.primitives.doubles.fromfloats;

import com.github.fge.multiterator.primitives.doubles.DoubleValuesSpliteratorBase;

public final class DoubleValuesSpliteratorFromFloats
    extends DoubleValuesSpliteratorBase<DoubleValuesFromFloats>
{
    private final float[] array;

    DoubleValuesSpliteratorFromFloats(final float[] array, final int inputSize,
        final int windowSize, final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
        this.array = array;
    }

    @Override
    protected DoubleValuesFromFloats initialValue()
    {
        return new DoubleValuesFromFloats(array, inputSize, windowSize);
    }
}
