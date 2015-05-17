package com.github.fge.multiterator.ints;

import com.github.fge.multiterator.internal.ValuesCore;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public abstract class IntValuesSpliteratorBase<V extends IntValuesBase<V>>
    implements Spliterator<IntValues>
{
    protected final int inputSize;
    protected final int windowSize;
    protected final UnaryOperator<V> operator;
    protected final boolean windowed;

    protected V currentValue = null;

    protected int currentIndex = 0;

    protected IntValuesSpliteratorBase(final int inputSize,
        final int windowSize, final boolean windowed)
    {
        this.inputSize = inputSize;
        this.windowSize = windowSize;
        operator = windowed ? ValuesCore::nextWindow : ValuesCore::shift;
        this.windowed = windowed;
    }

    protected abstract V initialValue();

    @Override
    public boolean tryAdvance(final Consumer<? super IntValues> action)
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
    public final Spliterator<IntValues> trySplit()
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
        return ORDERED | SIZED | NONNULL;
    }
}
