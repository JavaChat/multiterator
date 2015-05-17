package com.github.fge.multiterator.ints;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class IntMultiteratorBase
    implements IntMultiterator
{
    protected final int inputSize;
    protected final int windowSize;
    protected final boolean windowed;

    protected IntMultiteratorBase(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        this.inputSize = inputSize;
        this.windowSize = windowSize;
        this.windowed = windowed;
    }

    @Override
    public abstract Spliterator<IntValues> spliterator();

    @Override
    public final Stream<IntValues> stream()
    {
        return StreamSupport.stream(spliterator(), false);
    }
}
