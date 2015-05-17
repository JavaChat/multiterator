package com.github.fge.multiterator.primitives.doubles;

import com.github.fge.multiterator.DoubleValues;
import com.github.fge.multiterator.core.ValuesCore;

import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public abstract class DoubleValuesBase<V extends DoubleValuesBase<V>>
    extends ValuesCore<V>
    implements DoubleValues
{
    protected DoubleValuesBase(final int inputSize, final int windowSize)
    {
        super(inputSize, windowSize);
    }

    protected DoubleValuesBase(final V prev, final int offset)
    {
        super(prev, offset);
    }

    @Override
    public final double get(final int index)
    {
        if (index >= windowSize)
            throw new IndexOutOfBoundsException();
        return doGet(index);
    }

    protected abstract double doGet(final int index);

    @Override
    public final DoubleStream stream()
    {
        return IntStream.range(0, windowSize).mapToDouble(this::doGet);
    }

    @Override
    public final int hashCode()
    {
        return stream().mapToInt(Double::hashCode)
            .reduce(1, (x, y) -> 31 * x + y);
    }

    @Override
    public final boolean equals(final Object obj)
    {
        if (!(obj instanceof DoubleValues))
            return false; // also takes care of obj == null
        if (this == obj)
            return true;
        final DoubleValues other = (DoubleValues) obj;
        final IntPredicate predicate
            = index -> get(index) == other.get(index);
        return windowSize == other.size()
            && IntStream.range(0, windowSize).allMatch(predicate);
    }

    @Override
    public final String toString()
    {
        return '<' + stream().mapToObj(Double::toString)
            .collect(Collectors.joining(", ")) + '>';
    }
}
