package com.github.fge.multiterator.list;

import com.github.fge.multiterator.base.ValuesBase;

import java.util.List;
import java.util.stream.Stream;

public final class ListValues<T>
    extends ValuesBase<T, ListValues<T>>
{
    private final List<T> list;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    ListValues(final List<T> list, final int inputSize,
        final int windowSize)
    {
        super(inputSize, windowSize);
        this.list = list;
    }

    private ListValues(final ListValues<T> prev, final int offset)
    {
        super(prev, offset);
        list = prev.list;
    }

    @Override
    public T doGet(final int index)
    {
        return list.get(offset + index);
    }

    @Override
    public ListValues<T> shift()
    {
        return new ListValues<>(this, offset + 1);
    }

    @Override
    public ListValues<T> nextWindow()
    {
        return new ListValues<>(this, offset + windowSize);
    }

    @Override
    public Stream<T> stream()
    {
        return list.subList(offset, offset + windowSize).stream();
    }
}
