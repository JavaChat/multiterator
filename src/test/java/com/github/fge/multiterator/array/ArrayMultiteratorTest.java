package com.github.fge.multiterator.array;

import com.github.fge.multiterator.Multiterator;
import com.github.fge.multiterator.MultiteratorBuilder;
import com.github.fge.multiterator.MultiteratorTest;

import java.util.stream.Stream;

public final class ArrayMultiteratorTest
    extends MultiteratorTest
{
    public ArrayMultiteratorTest()
    {
        super(ArrayMultiterator.class);
    }

    @Override
    protected Multiterator<Integer> getMultiterator(
        final MultiteratorBuilder builder, final Stream<Integer> stream)
    {
        final Integer[] array = stream.toArray(Integer[]::new);
        return builder.over(array);
    }
}
