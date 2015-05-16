package com.github.fge.multiterator.array;

import com.github.fge.multiterator.Values;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.UnaryOperator;

final class ArrayValuesIterator<T>
    implements Iterator<Values<T>>
{
    private final T[] array;
    private final int listSize;
    private final int windowSize;
    private final UnaryOperator<ArrayValues<T>> operator;

    private ArrayValues<T> currentValue;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    ArrayValuesIterator(final T[] array, final int listSize,
        final int windowSize, final UnaryOperator<ArrayValues<T>> operator)
    {
        this.array = array;
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
            ? new ArrayValues<>(array, listSize, windowSize)
            : operator.apply(currentValue);
        return currentValue;
    }
}
