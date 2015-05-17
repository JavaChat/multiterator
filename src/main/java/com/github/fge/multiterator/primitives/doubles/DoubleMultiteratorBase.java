package com.github.fge.multiterator.primitives.doubles;

import com.github.fge.multiterator.DoubleMultiterator;
import com.github.fge.multiterator.DoubleValues;
import com.github.fge.multiterator.core.MultiteratorCore;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class DoubleMultiteratorBase
    extends MultiteratorCore
    implements DoubleMultiterator
{
    protected DoubleMultiteratorBase(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
    }

    @Override
    public abstract Spliterator<DoubleValues> spliterator();

    @Override
    public final Stream<DoubleValues> stream()
    {
        return StreamSupport.stream(spliterator(), false);
    }
}
