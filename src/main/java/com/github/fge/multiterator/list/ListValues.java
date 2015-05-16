package com.github.fge.multiterator.list;

import com.github.fge.multiterator.Values;

import java.util.List;

public final class ListValues<T>
    implements Values<T>
{
    private final int windowSize;
    private final int listSize;
    private final int offset;
    private final List<T> list;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    ListValues(final List<T> list, final int listSize,
        final int windowSize)
    {
        this.list = list;
        this.listSize = listSize;
        this.windowSize = windowSize;
        offset = 0;
    }

    private ListValues(final ListValues<T> prev, final int offset)
    {
        windowSize = prev.windowSize;
        listSize = prev.listSize;
        list = prev.list;
        this.offset = offset;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(final int index)
    {
        if (index >= windowSize)
            throw new IndexOutOfBoundsException();
        return list.get(offset + index);
    }

    boolean hasNext()
    {
        return offset + windowSize < listSize;
    }

    ListValues<T> shift()
    {
        return new ListValues<>(this, offset + 1);
    }

    ListValues<T> nextWindow()
    {
        return new ListValues<>(this, offset + windowSize);
    }
}
