package com.github.fge.multiterator.base;

import com.github.fge.multiterator.Values;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ValuesBase<T, V extends ValuesBase<T, V>>
    implements Values<T>
{
    protected final int inputSize;
    protected final int windowSize;
    protected final int offset;

    protected ValuesBase(final int inputSize, final int windowSize)
    {
        this.inputSize = inputSize;
        this.windowSize = windowSize;
        offset = 0;
    }

    protected ValuesBase(final V prev, final int offset)
    {
        inputSize = prev.inputSize;
        windowSize = prev.windowSize;
        this.offset = offset;
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
    public final int size()
    {
        return windowSize;
    }

    public final boolean hasNext()
    {
        return offset + windowSize < inputSize;
    }

    public abstract V shift();

    public abstract V nextWindow();

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
        final Values<T> other = (Values<T>) obj;
        return windowSize == other.size() && IntStream.range(0, windowSize)
            .allMatch(i -> Objects.equals(get(i), other.get(i)));
    }

    @Override
    public final String toString()
    {
        return stream().map(Object::toString)
            .collect(Collectors.joining(", "));
    }
}
