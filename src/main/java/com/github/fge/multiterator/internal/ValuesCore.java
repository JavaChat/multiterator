package com.github.fge.multiterator.internal;

public abstract class ValuesCore<V extends ValuesCore<V>>
{
    protected final int inputSize;
    protected final int windowSize;
    protected final int offset;

    protected ValuesCore(final int inputSize, final int windowSize)
    {
        this.inputSize = inputSize;
        this.windowSize = windowSize;
        offset = 0;
    }

    protected ValuesCore(final V prev, final int offset)
    {
        inputSize = prev.inputSize;
        windowSize = prev.windowSize;
        this.offset = offset;
    }

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
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();
}
