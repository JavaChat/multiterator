package com.github.fge.multiterator.list;

import com.github.fge.multiterator.base.ValuesIteratorBase;

import java.util.List;

final class ListValuesIterator<T>
    extends ValuesIteratorBase<T, ListValues<T>>
{
    private final List<T> list;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    ListValuesIterator(final List<T> list, final int inputSize,
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
