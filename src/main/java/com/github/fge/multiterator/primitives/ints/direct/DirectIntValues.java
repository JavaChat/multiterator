package com.github.fge.multiterator.primitives.ints.direct;

import com.github.fge.multiterator.core.VisibleForTesting;
import com.github.fge.multiterator.primitives.ints.IntValuesBase;

@VisibleForTesting
public final class DirectIntValues
    extends IntValuesBase<DirectIntValues>
{
    private final int[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public DirectIntValues(final int[] array, final int inputSize,
        final int windowSize)
    {
        super(inputSize, windowSize);
        this.array = array;
    }

    private DirectIntValues(final DirectIntValues prev, final int offset)
    {
        super(prev, offset);
        array = prev.array;
    }

    @Override
    public int doGet(final int index)
    {
        return array[offset + index];
    }

    @Override
    public DirectIntValues shift()
    {
        return new DirectIntValues(this, offset + 1);
    }

    @Override
    public DirectIntValues nextWindow()
    {
        return new DirectIntValues(this, offset + windowSize);
    }
}
