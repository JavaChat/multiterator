package com.github.fge.multiterator.ints;

final class IntValuesFromChars
    extends IntValuesBase<IntValuesFromChars>
{
    private final char[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    IntValuesFromChars(final char[] array, final int inputSize,
        final int windowSize)
    {
        super(inputSize, windowSize);
        this.array = array;
    }

    private IntValuesFromChars(final IntValuesFromChars prev, final int offset)
    {
        super(prev, offset);
        array = prev.array;
    }

    @Override
    public int doGet(final int index)
    {
        return array[offset + index];
    }

    @Override
    public IntValuesFromChars shift()
    {
        return new IntValuesFromChars(this, offset + 1);
    }

    @Override
    public IntValuesFromChars nextWindow()
    {
        return new IntValuesFromChars(this, offset + windowSize);
    }

}
