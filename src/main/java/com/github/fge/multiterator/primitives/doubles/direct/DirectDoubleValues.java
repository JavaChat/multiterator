package com.github.fge.multiterator.primitives.doubles.direct;

import com.github.fge.multiterator.core.VisibleForTesting;
import com.github.fge.multiterator.primitives.doubles.DoubleValuesBase;

@VisibleForTesting
public final class DirectDoubleValues
    extends DoubleValuesBase<DirectDoubleValues>
{
    private final double[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public DirectDoubleValues(final double[] array, final int inputSize,
        final int windowSize)
    {
        super(inputSize, windowSize);
        this.array = array;
    }

    private DirectDoubleValues(final DirectDoubleValues prev, final int offset)
    {
        super(prev, offset);
        array = prev.array;
    }

    @Override
    public double doGet(final int index)
    {
        return array[offset + index];
    }

    @Override
    public DirectDoubleValues shift()
    {
        return new DirectDoubleValues(this, offset + 1);
    }

    @Override
    public DirectDoubleValues nextWindow()
    {
        return new DirectDoubleValues(this, offset + windowSize);
    }
}
