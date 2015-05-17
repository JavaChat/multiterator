package com.github.fge.multiterator.primitives.ints;

import com.github.fge.multiterator.IntMultiterator;
import com.github.fge.multiterator.MultiteratorBuilder;
import com.github.fge.multiterator.primitives.ints.asis.IntMultiteratorAsIs;

import java.util.stream.IntStream;

public final class IntMultiteratorAsIsTest
    extends IntMultiteratorTest
{
    public IntMultiteratorAsIsTest()
    {
        super(IntMultiteratorAsIs.class);
    }

    @Override
    protected IntMultiterator getMultiterator(final MultiteratorBuilder builder,
        final IntStream stream)
    {
        return builder.over(stream.toArray());
    }
}
