package com.github.fge.multiterator.internal;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public abstract class ValuesSpliteratorCore<V extends ValuesCore<V>>
{
    protected final int inputSize;
    protected final int windowSize;
    protected final UnaryOperator<V> operator;
    protected final boolean windowed;

    protected V currentValue = null;

    protected int currentIndex = 0;

    protected ValuesSpliteratorCore(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        this.inputSize = inputSize;
        this.windowSize = windowSize;
        operator = windowed ? ValuesCore::nextWindow : ValuesCore::shift;
        this.windowed = windowed;
    }

    protected abstract V initialValue();

    protected final boolean doTryAdvance(final Consumer<? super V> action)
    {
        if (currentValue != null && !currentValue.hasNext())
            return false;

        currentValue = currentValue == null ? initialValue()
            : operator.apply(currentValue);
        action.accept(currentValue);
        currentIndex += windowed ? windowSize : 1;
        return true;
    }

    protected final Spliterator<V> doTrySplit()
    {
        return null;
    }

    protected final long doEstimateSize()
    {
        final long ret = inputSize - currentIndex;
        return windowed ? ret / windowSize : ret;
    }

    protected final int doCharacteristics()
    {
        return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.NONNULL;
    }
}
