package com.github.fge.multiterator.core;

public abstract class MultiteratorCore
{
    protected final int inputSize;
    protected final int windowSize;
    protected final boolean windowed;

    protected MultiteratorCore(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        this.inputSize = inputSize;
        this.windowSize = windowSize;
        this.windowed = windowed;
    }
}
