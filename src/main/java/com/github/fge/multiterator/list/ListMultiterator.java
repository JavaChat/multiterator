package com.github.fge.multiterator.list;

import com.github.fge.multiterator.Values;
import com.github.fge.multiterator.base.MultiteratorBase;

import java.util.List;
import java.util.Spliterator;

public final class ListMultiterator<T>
    extends MultiteratorBase<T, ListValues<T>>
{
    private final List<T> list;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public ListMultiterator(final List<T> list, final int windowSize,
        final boolean windowed)
    {
        super(list.size(), windowSize, windowed);
        this.list = list;
    }

    @Override
    public Spliterator<Values<T>> spliterator()
    {
        return new ListValuesSpliterator<>(list, inputSize, windowSize,
            windowed);
    }
}
