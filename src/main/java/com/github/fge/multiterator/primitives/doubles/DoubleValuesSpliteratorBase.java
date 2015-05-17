package com.github.fge.multiterator.primitives.doubles;

import com.github.fge.multiterator.DoubleValues;
import com.github.fge.multiterator.core.ValuesSpliteratorCore;

import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class DoubleValuesSpliteratorBase<V extends DoubleValuesBase<V>>
    extends ValuesSpliteratorCore<V>
    implements Spliterator<DoubleValues>
{
    protected DoubleValuesSpliteratorBase(final int inputSize,
        final int windowSize, final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
    }

    @Override
    public boolean tryAdvance(final Consumer<? super DoubleValues> action)
    {
        return doTryAdvance(action);
    }

    @Override
    public final Spliterator<DoubleValues> trySplit()
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
