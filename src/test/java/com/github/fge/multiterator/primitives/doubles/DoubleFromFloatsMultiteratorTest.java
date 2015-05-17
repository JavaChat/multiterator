package com.github.fge.multiterator.primitives.doubles;

import com.github.fge.multiterator.DoubleMultiterator;
import com.github.fge.multiterator.MultiteratorBuilder;
import com.github.fge.multiterator.primitives.doubles.fromfloats.DoubleFromFloatsMultiterator;

import java.util.stream.DoubleStream;

public final class DoubleFromFloatsMultiteratorTest
    extends DoubleMultiteratorTest
{
    public DoubleFromFloatsMultiteratorTest()
    {
        super(DoubleFromFloatsMultiterator.class);
    }

    @Override
    protected DoubleMultiterator getMultiterator(
        final MultiteratorBuilder builder, final DoubleStream stream)
    {
        final double[] doubles = stream.toArray();
        final int length = doubles.length;
        final float[] floats = new float[length];
        for (int i = 0; i < length; i++)
            floats[i] = (float) doubles[i];
        return builder.over(floats);
    }
}
