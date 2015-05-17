package com.github.fge.multiterator.ints;

import java.util.Spliterator;

public final class IntMultiteratorAsIs
    extends IntMultiteratorBase
{
    private final int[] array;

    public IntMultiteratorAsIs(final int[] array , final int windowSize,
        final boolean windowed)
    {
        super(array.length, windowSize, windowed);
        this.array = array;
    }

    @Override
    public Spliterator<IntValues> spliterator()
    {
        return new IntValuesSpliteratorAsIs(array, inputSize, windowSize,
            windowed);
    }
}
