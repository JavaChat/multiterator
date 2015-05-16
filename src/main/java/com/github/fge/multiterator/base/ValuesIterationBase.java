package com.github.fge.multiterator.base;

import java.util.function.UnaryOperator;

public abstract class ValuesIterationBase<T, V extends ValuesBase<T, V>>
{
    protected final int inputSize;
    protected final int windowSize;
    protected final UnaryOperator<V> operator;

    protected V currentValue = null;

    protected ValuesIterationBase(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        this.inputSize = inputSize;
        this.windowSize = windowSize;
        operator = windowed ? ValuesBase::nextWindow : ValuesBase::shift;
    }

    protected abstract V initialValue();
}
