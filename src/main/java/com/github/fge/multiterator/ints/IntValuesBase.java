package com.github.fge.multiterator.ints;

import com.github.fge.multiterator.internal.ValuesCore;

import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class IntValuesBase<V extends IntValuesBase<V>>
    extends ValuesCore<V>
    implements IntValues
{
    protected IntValuesBase(final int inputSize, final int windowSize)
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
        return doGet(index);
    }

    protected abstract int doGet(final int index);

    @Override
    public final IntStream stream()
    {
        return IntStream.range(0, windowSize).map(this::doGet);
    }

    @Override
    public final int hashCode()
    {
        return stream().reduce(1, (x, y) -> 31 * x + y);
    }

    @Override
    public final boolean equals(final Object obj)
    {
        if (!(obj instanceof IntValues))
            return false; // also takes care of obj == null
        if (this == obj)
            return true;
        final IntValues other = (IntValues) obj;
        final IntPredicate predicate
            = index -> get(index) == other.get(index);
        return windowSize == other.size()
            && IntStream.range(0, windowSize).allMatch(predicate);
    }

    @Override
    public final String toString()
    {
        return '<' + stream().mapToObj(Integer::toString)
            .collect(Collectors.joining(", ")) + '>';
    }
}
