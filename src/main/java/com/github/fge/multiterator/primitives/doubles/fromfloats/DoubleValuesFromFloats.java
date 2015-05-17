package com.github.fge.multiterator.primitives.doubles.fromfloats;

import com.github.fge.multiterator.core.VisibleForTesting;
import com.github.fge.multiterator.primitives.doubles.DoubleValuesBase;

@VisibleForTesting
public final class DoubleValuesFromFloats
    extends DoubleValuesBase<DoubleValuesFromFloats>
{
    private final float[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public DoubleValuesFromFloats(final float[] array, final int inputSize,
        final int windowSize)
    {
        super(inputSize, windowSize);
        this.array = array;
    }

    private DoubleValuesFromFloats(final DoubleValuesFromFloats prev,
        final int offset)
    {
        super(prev, offset);
        array = prev.array;
    }

    @Override
    public double doGet(final int index)
    {
        // Rely on automatic primitive widening here
        return array[offset + index];
    }

    @Override
    public DoubleValuesFromFloats shift()
    {
        return new DoubleValuesFromFloats(this, offset + 1);
    }

    @Override
    public DoubleValuesFromFloats nextWindow()
    {
        return new DoubleValuesFromFloats(this, offset + windowSize);
    }
}
