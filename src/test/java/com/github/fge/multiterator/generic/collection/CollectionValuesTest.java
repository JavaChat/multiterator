package com.github.fge.multiterator.generic.collection;

import com.github.fge.multiterator.ValuesTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CollectionValuesTest
    extends ValuesTest<CollectionValues<Integer>>

{
    @Override
    protected CollectionValues<Integer> getValues(final Stream<Integer> stream,
        final int windowSize)
    {
        final List<Integer> list = stream.collect(Collectors.toList());
        return new CollectionValues<>(list.iterator(), list.size(), windowSize);
    }
}
