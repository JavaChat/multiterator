package com.github.fge.multiterator.list;

import com.github.fge.multiterator.ValuesTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ListValuesTest
    extends ValuesTest<ListValues<Integer>>

{
    @Override
    protected ListValues<Integer> getValues(final Stream<Integer> stream,
        final int windowSize)
    {
        final List<Integer> list = stream.collect(Collectors.toList());
        return new ListValues<>(list, list.size(), windowSize);
    }
}
