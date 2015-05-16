package com.github.fge.multiterator.base;

import com.github.fge.multiterator.Multiterator;

import java.util.function.UnaryOperator;

public abstract class MultiteratorBase<T, V extends ValuesBase<T, V>>
    implements Multiterator<T>
{
    protected final int inputSize;
    protected final int windowSize;
    protected final UnaryOperator<V> operator;

    protected MultiteratorBase(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        this.inputSize = inputSize;
        this.windowSize = windowSize;
        operator = windowed ? ValuesBase::nextWindow : ValuesBase::shift;
    }
}
