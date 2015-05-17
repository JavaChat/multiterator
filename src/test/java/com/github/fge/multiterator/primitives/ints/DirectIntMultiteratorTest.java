package com.github.fge.multiterator.primitives.ints;

import com.github.fge.multiterator.IntMultiterator;
import com.github.fge.multiterator.MultiteratorBuilder;
import com.github.fge.multiterator.primitives.ints.direct.DirectIntMultiterator;

import java.util.stream.IntStream;

public final class DirectIntMultiteratorTest
    extends IntMultiteratorTest
{
    public DirectIntMultiteratorTest()
    {
        super(DirectIntMultiterator.class);
    }

    @Override
    protected IntMultiterator getMultiterator(final MultiteratorBuilder builder,
        final IntStream stream)
    {
        return builder.over(stream.toArray());
    }
}
