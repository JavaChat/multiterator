package com.github.fge.multiterator.base;

import com.github.fge.multiterator.Multiterator;

public abstract class MultiteratorBase<T, V extends ValuesBase<T, V>>
    implements Multiterator<T>
{
    protected final int inputSize;
    protected final int windowSize;
    protected final boolean windowed;

    protected MultiteratorBase(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        this.inputSize = inputSize;
        this.windowSize = windowSize;
        this.windowed = windowed;
    }
}
