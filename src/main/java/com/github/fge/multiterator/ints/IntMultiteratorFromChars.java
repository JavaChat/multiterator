package com.github.fge.multiterator.ints;

import java.util.Spliterator;

public final class IntMultiteratorFromChars
    extends IntMultiteratorBase
{
    private final char[] array;

    public IntMultiteratorFromChars(final char[] array, final int windowSize,
        final boolean windowed)
    {
        super(array.length, windowSize, windowed);
        this.array = array;
    }

    @Override
    public Spliterator<IntValues> spliterator()
    {
        return new IntValuesSpliteratorFromChars(array, inputSize, windowSize,
            windowed);
    }
}
