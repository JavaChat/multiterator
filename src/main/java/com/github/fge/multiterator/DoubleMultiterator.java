package com.github.fge.multiterator;

import java.util.Iterator;
import java.util.stream.Stream;

public interface DoubleMultiterator
    extends Iterable<DoubleValues>
{
    Stream<DoubleValues> stream();

    @Override
    default Iterator<DoubleValues> iterator()
    {
        return stream().iterator();
    }
}
