package com.github.fge.multiterator.ints;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

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
    public IntUnaryOperator intAt()
    {
        return index -> array[offset + index];
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

    @Override
    public IntStream stream()
    {
        final int[] subArray
            = Arrays.copyOfRange(array, offset, offset + windowSize);
        return Arrays.stream(subArray);
    }

    public static void main(final String... args)
    {
        final int[] source = new int[] { 4, 298, 63, 938, 99, -6387, 34 };
        final IntValues values = new IntValuesAsIs(source, 7, 3);

        values.stream().mapToObj(Integer::toString).forEach(System.out::println);
    }
}
