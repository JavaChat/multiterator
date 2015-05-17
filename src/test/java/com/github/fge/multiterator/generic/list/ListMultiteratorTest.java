package com.github.fge.multiterator.generic.list;

import com.github.fge.multiterator.Multiterator;
import com.github.fge.multiterator.MultiteratorBuilder;
import com.github.fge.multiterator.MultiteratorTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ListMultiteratorTest
    extends MultiteratorTest
{
    public ListMultiteratorTest()
    {
        super(ListMultiterator.class);
    }

    @Override
    protected Multiterator<Integer> getMultiterator(
        final MultiteratorBuilder builder, final Stream<Integer> stream)
    {
        return builder.over(stream.collect(Collectors.toList()));
    }
}
