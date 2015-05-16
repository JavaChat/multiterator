package com.github.fge.multiterator.array;

import com.github.fge.multiterator.base.ValuesIteratorBase;

import java.util.function.UnaryOperator;

final class ArrayValuesIterator<T>
    extends ValuesIteratorBase<T, ArrayValues<T>>
{
    private final T[] array;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    ArrayValuesIterator(final T[] array, final int inputSize,
        final int windowSize, final UnaryOperator<ArrayValues<T>> operator)
    {
        super(inputSize, windowSize, operator);
        this.array = array;
    }

    @Override
    protected ArrayValues<T> initialValue()
    {
        return new ArrayValues<>(array, inputSize, windowSize);
    }
}
