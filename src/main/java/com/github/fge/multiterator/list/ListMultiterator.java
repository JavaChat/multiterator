package com.github.fge.multiterator.list;

import com.github.fge.multiterator.Values;
import com.github.fge.multiterator.base.MultiteratorBase;

import java.util.Iterator;
import java.util.List;

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
    public Iterator<Values<T>> iterator()
    {
        return new ListValuesIterator<>(list, inputSize, windowSize, windowed);
    }
}
