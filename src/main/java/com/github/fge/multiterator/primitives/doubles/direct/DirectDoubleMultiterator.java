package com.github.fge.multiterator.primitives.doubles.direct;

import com.github.fge.multiterator.DoubleValues;
import com.github.fge.multiterator.primitives.doubles.DoubleMultiteratorBase;

import java.util.Spliterator;

public final class DirectDoubleMultiterator
    extends DoubleMultiteratorBase
{
    private final double[] array;

    public DirectDoubleMultiterator(final double[] array, final int windowSize,
        final boolean windowed)
    {
        super(array.length, windowSize, windowed);
        this.array = array;
    }

    @Override
    public Spliterator<DoubleValues> spliterator()
    {
        return new DirectDoubleValuesSpliterator(array, inputSize, windowSize,
            windowed);
    }
}
