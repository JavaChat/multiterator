package com.github.fge.multiterator.generic;

import com.github.fge.multiterator.Values;
import com.github.fge.multiterator.core.ValuesSpliteratorCore;

import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class GenericValuesSpliterator<T, V extends GenericValues<T, V>>
    extends ValuesSpliteratorCore<V>
    implements Spliterator<Values<T>>
{
    protected GenericValuesSpliterator(final int inputSize,
        final int windowSize, final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
    }

    @Override
    public boolean tryAdvance(final Consumer<? super Values<T>> action)
    {
        return doTryAdvance(action);
    }

    @Override
    public Spliterator<Values<T>> trySplit()
    {
        return null;
    }

    @Override
    public long estimateSize()
    {
        return doEstimateSize();
    }

    @Override
    public int characteristics()
    {
        return doCharacteristics();
    }
}
