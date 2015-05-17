package com.github.fge.multiterator.generic.collection;

import com.github.fge.multiterator.Multiterator;
import com.github.fge.multiterator.MultiteratorBuilder;
import com.github.fge.multiterator.MultiteratorTest;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CollectionMultiteratorTest
    extends MultiteratorTest
{
    public CollectionMultiteratorTest()
    {
        super(CollectionMultiterator.class);
    }

    @Override
    protected Multiterator<Integer> getMultiterator(
        final MultiteratorBuilder builder, final Stream<Integer> stream)
    {
        final Collection<Integer> collection
            = stream.collect(Collectors.toList());
        return builder.over(collection);
    }
}
