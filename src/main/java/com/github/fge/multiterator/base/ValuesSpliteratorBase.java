package com.github.fge.multiterator.base;

import com.github.fge.multiterator.Values;

import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class ValuesSpliteratorBase<T, V extends ValuesBase<T, V>>
    extends ValuesIterationBase<T, V>
    implements Spliterator<Values<T>>
{
    protected final boolean windowed;

    protected V currentValue = null;
    protected int currentIndex = 0;

    protected ValuesSpliteratorBase(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
        this.windowed = windowed;
    }

    @Override
    public boolean tryAdvance(final Consumer<? super Values<T>> action)
    {
        if (currentValue != null && !currentValue.hasNext())
            return false;

        currentValue = currentValue == null ? initialValue()
            : operator.apply(currentValue);
        action.accept(currentValue);
        currentIndex += windowed ? windowSize : 1;
        return true;
    }

    @Override
    public final Spliterator<Values<T>> trySplit()
    {
        return null;
    }

    @Override
    public final long estimateSize()
    {
        final long ret = inputSize - currentIndex;
        return windowed ? ret / windowSize : ret;
    }

    @Override
    public final int characteristics()
    {
        return ORDERED | SIZED;
    }
}
