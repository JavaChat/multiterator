package com.github.fge.multiterator.ints;

public final class IntValuesSpliteratorFromChars
    extends IntValuesSpliteratorBase<IntValuesFromChars>
{
    private final char[] array;

    IntValuesSpliteratorFromChars(final char[] array, final int inputSize,
        final int windowSize, final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
        this.array = array;
    }

    @Override
    protected IntValuesFromChars initialValue()
    {
        return new IntValuesFromChars(array, inputSize, windowSize);
    }
}
