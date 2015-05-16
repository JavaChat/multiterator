package com.github.fge.multiterator.list;

import com.github.fge.multiterator.Multiterator;
import com.github.fge.multiterator.Values;

import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;

public final class ListMultiterator<T>
    implements Multiterator<T>
{
    private final List<T> list;
    private final int size;
    private final int windowSize;
    private final UnaryOperator<ListValues<T>> operator;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public ListMultiterator(final List<T> list, final int windowSize,
        final boolean windowed)
    {
        this.list = list;
        size = list.size();
        this.windowSize = windowSize;
        operator = windowed ? ListValues::nextWindow : ListValues::shift;
    }

    @Override
    public Iterator<Values<T>> iterator()
    {
        return new ListValuesIterator<>(list, size, windowSize, operator);
    }
}
