package com.github.fge.multiterator.generic.list;

import com.github.fge.multiterator.generic.GenericValuesSpliterator;

import java.util.List;

final class ListValuesSpliterator<T>
    extends GenericValuesSpliterator<T, ListValues<T>>
{
    private final List<T> list;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    ListValuesSpliterator(final List<T> list, final int inputSize,
        final int windowSize, final boolean windowed)
    {
        super(inputSize, windowSize, windowed);
        this.list = list;
    }

    @Override
    protected ListValues<T> initialValue()
    {
        return new ListValues<>(list, inputSize, windowSize);
    }
}
