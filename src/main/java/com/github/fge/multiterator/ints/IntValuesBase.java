package com.github.fge.multiterator.ints;

import com.github.fge.multiterator.internal.ValuesCore;

import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

abstract class IntValuesBase<V extends IntValuesBase<V>>
    extends ValuesCore<V>
    implements IntValues
{
    IntValuesBase(final int inputSize, final int windowSize)
    {
        super(inputSize, windowSize);
    }

    protected IntValuesBase(final V prev, final int offset)
    {
        super(prev, offset);
    }

    @Override
    public final int get(final int index)
    {
        if (index >= windowSize)
            throw new IndexOutOfBoundsException();
        return intAt().applyAsInt(index);
    }

    public abstract IntUnaryOperator intAt();

    @Override
    public final int hashCode()
    {
        return IntStream.range(offset, offset + windowSize)
            .map(this::get)
            .reduce(1, (x, y) -> 31 * x + y);
    }

    @Override
    public final boolean equals(final Object obj)
    {
        if (!(obj instanceof IntValues))
            return false; // also takes care of obj == null
        if (this == obj)
            return true;
        final IntValues other = (IntValues) obj;
        return IntStream.range(0, windowSize). allMatch(
            index -> get(index) == other.get(index)
        );
    }

    @Override
    public final String toString()
    {
        return '<' + IntStream.range(0, windowSize).map(this::get)
            .mapToObj(Integer::toString).collect(Collectors.joining(", "))
            + '>';
    }
}
