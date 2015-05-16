package com.github.fge.multiterator.list;

import com.github.fge.multiterator.Values;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.UnaryOperator;

final class ListValuesIterator<T>
    implements Iterator<Values<T>>
{
    private final List<T> list;
    private final int listSize;
    private final int windowSize;
    private final UnaryOperator<ListValues<T>> operator;

    private ListValues<T> currentValue;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    ListValuesIterator(final List<T> list, final int listSize,
        final int windowSize, final UnaryOperator<ListValues<T>> operator)
    {
        this.list = list;
        this.listSize = listSize;
        this.windowSize = windowSize;
        this.operator = operator;
    }

    @Override
    public boolean hasNext()
    {
        return currentValue == null || currentValue.hasNext();
    }

    @Override
    public Values<T> next()
    {
        if (!hasNext())
            throw new NoSuchElementException();
        currentValue = currentValue == null
            ? new ListValues<>(list, listSize, windowSize)
            : operator.apply(currentValue);
        return currentValue;
    }
}
