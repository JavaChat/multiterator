package com.github.fge.multiterator.array;

import com.github.fge.multiterator.ValuesTest;

import java.util.stream.Stream;

public final class ArrayValuesTest
    extends ValuesTest<ArrayValues<Integer>>

{
    @Override
    protected ArrayValues<Integer> getValues(final Stream<Integer> stream,
        final int windowSize)
    {
        final Integer[] array = stream.toArray(Integer[]::new);
        return new ArrayValues<>(array, array.length, windowSize);
    }
}
