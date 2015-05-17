package com.github.fge.multiterator.ints;

import com.github.fge.multiterator.IntMultiterator;
import com.github.fge.multiterator.MultiteratorBuilder;
import com.github.fge.multiterator.ints.fromchars.IntMultiteratorFromChars;

import java.util.stream.IntStream;

public final class IntMultiteratorFromCharsTest
    extends IntMultiteratorTest
{
    public IntMultiteratorFromCharsTest()
    {
        super(IntMultiteratorFromChars.class);
    }

    @Override
    protected IntMultiterator getMultiterator(final MultiteratorBuilder builder,
        final IntStream stream)
    {
        final int[] ints = stream.toArray();
        final int length = ints.length;
        final char[] chars = new char[length];
        for (int i = 0; i < length; i++)
            chars[i] = (char) ints[i];
        return builder.over(chars);
    }
}
