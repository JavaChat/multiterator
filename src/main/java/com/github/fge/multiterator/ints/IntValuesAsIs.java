package com.github.fge.multiterator.ints;

final class IntValuesAsIs
    extends IntValuesBase<IntValuesAsIs>
{
    private final int[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    IntValuesAsIs(final int[] array, final int inputSize, final int windowSize)
    {
        super(inputSize, windowSize);
        this.array = array;
    }

    private IntValuesAsIs(final IntValuesAsIs prev, final int offset)
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
    public IntValuesAsIs shift()
    {
        return new IntValuesAsIs(this, offset + 1);
    }

    @Override
    public IntValuesAsIs nextWindow()
    {
        return new IntValuesAsIs(this, offset + windowSize);
    }
}
