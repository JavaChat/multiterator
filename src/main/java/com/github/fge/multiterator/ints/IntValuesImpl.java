package com.github.fge.multiterator.ints;

import java.util.Arrays;
import java.util.stream.IntStream;

public final class IntValuesImpl
    implements IntValues
{
    private final int[] array;
    private final int inputSize;
    private final int windowSize;
    private final int offset;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    IntValuesImpl(final int[] array, final int inputSize, final int windowSize)
    {
        this.array = array;
        this.inputSize = inputSize;
        this.windowSize = windowSize;
        offset = 0;
    }

    private IntValuesImpl(final IntValuesImpl prev, final int offset)
    {
        array = prev.array;
        inputSize = prev.inputSize;
        windowSize = prev.windowSize;
        this.offset = offset;
    }

    @Override
    public int get(final int index)
    {
        if (index >= windowSize)
            throw new IndexOutOfBoundsException();
        return array[offset + index];
    }

    @Override
    public int size()
    {
        return windowSize;
    }

    public IntValuesImpl shift()
    {
        return new IntValuesImpl(this, offset + 1);
    }

    public IntValuesImpl nextWindow()
    {
        return new IntValuesImpl(this, offset + windowSize);
    }

    @Override
    public IntStream stream()
    {
        final int[] subArray
            = Arrays.copyOfRange(array, offset, offset + windowSize);
        return Arrays.stream(subArray);
    }
}
