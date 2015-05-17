package com.github.fge.multiterator.base;

import com.github.fge.multiterator.Values;
import com.github.fge.multiterator.internal.ValuesCore;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ValuesBase<T, V extends ValuesBase<T, V>>
    extends ValuesCore<V>
    implements Values<T>
{
    protected ValuesBase(final int inputSize, final int windowSize)
    {
        super(inputSize, windowSize);
    }

    protected ValuesBase(final V prev, final int offset)
    {
        super(prev, offset);
    }

    @Override
    public T get(final int index)
    {
        if (index >= windowSize)
            throw new IndexOutOfBoundsException();
        return doGet(index);
    }

    protected abstract T doGet(int index);

    @Override
    public final int hashCode()
    {
        return stream().mapToInt(Objects::hashCode)
            .reduce(1, (x, y) -> 31 * x + y);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final boolean equals(final Object obj)
    {
        if (!(obj instanceof Values))
            return false; // also takes care of obj == null
        if (this == obj)
            return true;
        final V other = (V) obj;
        return windowSize == other.size() && IntStream.range(0, windowSize)
            .allMatch(i -> Objects.equals(get(i), other.get(i)));
    }

    @Override
    public final String toString()
    {
        return '<' + stream().map(Object::toString)
            .collect(Collectors.joining(", ")) + '>';
    }
}
