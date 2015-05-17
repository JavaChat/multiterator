package com.github.fge.multiterator.primitives.doubles.fromfloats;

import com.github.fge.multiterator.DoubleValues;
import com.github.fge.multiterator.primitives.doubles.DoubleMultiteratorBase;

import java.util.Spliterator;

public final class DoubleFromFloatsMultiterator
    extends DoubleMultiteratorBase
{
    private final float[] array;

    public DoubleFromFloatsMultiterator(final float[] array,
        final int windowSize, final boolean windowed)
    {
        super(array.length, windowSize, windowed);
        this.array = array;
    }

    @Override
    public Spliterator<DoubleValues> spliterator()
    {
        return new DoubleValuesSpliteratorFromFloats(array, inputSize,
            windowSize, windowed);
    }
}
