package com.github.fge.multiterator.base;

import com.github.fge.multiterator.Values;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.UnaryOperator;

public abstract class ValuesIteratorBase<T, V extends ValuesBase<T, V>>
    implements Iterator<Values<T>>
{
    protected final int inputSize;
    protected final int windowSize;
    protected final UnaryOperator<V> operator;

    protected V currentValue = null;

    protected ValuesIteratorBase(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        this.inputSize = inputSize;
        this.windowSize = windowSize;
        operator = windowed ? ValuesBase::nextWindow : ValuesBase::shift;
    }

    protected abstract V initialValue();

    @Override
    public final boolean hasNext()
    {
        return currentValue == null || currentValue.hasNext();
    }

    @Override
    public final Values<T> next()
    {
        if (!hasNext())
            throw new NoSuchElementException();
        currentValue = currentValue == null
            ? initialValue()
            : operator.apply(currentValue);
        return currentValue;
    }
}
