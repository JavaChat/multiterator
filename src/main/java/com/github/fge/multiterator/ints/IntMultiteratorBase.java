package com.github.fge.multiterator.ints;

import com.github.fge.multiterator.IntMultiterator;
import com.github.fge.multiterator.IntValues;
import com.github.fge.multiterator.core.MultiteratorCore;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class IntMultiteratorBase
    extends MultiteratorCore
    implements IntMultiterator
{
    protected IntMultiteratorBase(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
    }

    @Override
    public abstract Spliterator<IntValues> spliterator();

    @Override
    public final Stream<IntValues> stream()
    {
        return StreamSupport.stream(spliterator(), false);
    }
}
