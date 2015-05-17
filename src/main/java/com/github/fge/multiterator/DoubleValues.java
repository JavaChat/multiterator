package com.github.fge.multiterator;

import java.util.Iterator;
import java.util.stream.DoubleStream;

public interface DoubleValues
    extends Iterable<Double>
{
    double get(int index);

    default double first()
    {
        return get(0);
    }

    default double second()
    {
        return get(1);
    }

    int size();

    DoubleStream stream();

    @Override
    default Iterator<Double> iterator()
    {
        return stream().boxed().iterator();
    }

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    @Override
    String toString();
}
