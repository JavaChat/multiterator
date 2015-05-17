package com.github.fge.multiterator;

import java.util.Iterator;
import java.util.stream.Stream;

public interface IntMultiterator
    extends Iterable<IntValues>
{
    Stream<IntValues> stream();

    @Override
    default Iterator<IntValues> iterator()
    {
        return stream().iterator();
    }
}
