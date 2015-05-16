package com.github.fge.multiterator;

import java.util.stream.Stream;

public interface Values<T>
{
    T get(int index);

    default T first()
    {
        return get(0);
    }

    default T second()
    {
        return get(1);
    }

    Stream<T> stream();
}
