package com.github.fge.multiterator.primitives.doubles;

import com.github.fge.multiterator.DoubleMultiterator;
import com.github.fge.multiterator.MultiteratorBuilder;
import com.github.fge.multiterator.primitives.doubles.direct
    .DirectDoubleMultiterator;

import java.util.stream.DoubleStream;

public final class DirectDoubleMultiteratorTest
    extends DoubleMultiteratorTest
{
    public DirectDoubleMultiteratorTest()
    {
        super(DirectDoubleMultiterator.class);
    }

    @Override
    protected DoubleMultiterator getMultiterator(
        final MultiteratorBuilder builder, final DoubleStream stream)
    {
        return builder.over(stream.toArray());
    }
}
