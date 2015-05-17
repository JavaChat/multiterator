package com.github.fge.multiterator.primitives.ints;

import com.github.fge.multiterator.IntValues;
import com.github.fge.multiterator.core.ValuesSpliteratorCore;

import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class IntValuesSpliteratorBase<V extends IntValuesBase<V>>
    extends ValuesSpliteratorCore<V>
    implements Spliterator<IntValues>
{
    protected IntValuesSpliteratorBase(final int inputSize,
        final int windowSize, final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
    }

    @Override
    public boolean tryAdvance(final Consumer<? super IntValues> action)
    {
        return doTryAdvance(action);
    }

    @Override
    public final Spliterator<IntValues> trySplit()
    {
        return null;
    }

    @Override
    public final long estimateSize()
    {
        return doEstimateSize();
    }

    @Override
    public final int characteristics()
    {
        return doCharacteristics();
    }
}
