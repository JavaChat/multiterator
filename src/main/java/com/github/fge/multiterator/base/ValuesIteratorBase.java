package com.github.fge.multiterator.base;

import com.github.fge.multiterator.Values;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class ValuesIteratorBase<T, V extends ValuesBase<T, V>>
    extends ValuesIterationBase<T, V>
    implements Iterator<Values<T>>
{
    protected ValuesIteratorBase(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
    }

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
