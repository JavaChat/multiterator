package com.github.fge.multiterator.base;

import com.github.fge.multiterator.Multiterator;
import com.github.fge.multiterator.Values;
import com.github.fge.multiterator.internal.MultiteratorCore;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class GenericMultiterator<T>
    extends MultiteratorCore
    implements Multiterator<T>
{
    protected GenericMultiterator(final int inputSize, final int windowSize,
        final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
    }

    @Override
    public abstract Spliterator<Values<T>> spliterator();

    @Override
    public final Stream<Values<T>> stream()
    {
        return StreamSupport.stream(spliterator(), false);
    }
}
