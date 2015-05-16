package com.github.fge.multiterator;

@FunctionalInterface
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
}
