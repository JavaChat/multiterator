package com.github.fge.multiterator.collection;

import com.github.fge.multiterator.Multiterator;
import com.github.fge.multiterator.Values;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public final class CollectionMultiteratorTest
{
    @Test
    public void shiftIterationTest()
    {
        final Collection<Integer> list = IntStream.rangeClosed(0, 2)
            .boxed()
            .collect(Collectors.toList());

        final Multiterator<Integer> multiterator = Multiterator.ofSize(2)
            .over(list);

        final Iterator<Values<Integer>> iterator = multiterator.iterator();
        Values<Integer> values;

        assertThat(iterator.hasNext());

        values = iterator.next();

        assertThat(values.get(0)).isEqualTo(0);
        assertThat(values.get(1)).isEqualTo(1);

        assertThat(iterator.hasNext()).isTrue();

        values = iterator.next();

        assertThat(values.get(0)).isEqualTo(1);
        assertThat(values.get(1)).isEqualTo(2);

        assertThat(iterator.hasNext()).isFalse();

        assertThatThrownBy(iterator::next)
            .isExactlyInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void windowIterationTest()
    {
        final Collection<Integer> list = IntStream.rangeClosed(0, 3)
            .boxed()
            .collect(Collectors.toList());

        final Multiterator<Integer> multiterator = Multiterator.ofSize(2)
            .windowed().over(list);

        final Iterator<Values<Integer>> iterator = multiterator.iterator();
        Values<Integer> values;

        assertThat(iterator.hasNext());

        values = iterator.next();

        assertThat(values.get(0)).isEqualTo(0);
        assertThat(values.get(1)).isEqualTo(1);

        assertThat(iterator.hasNext()).isTrue();

        values = iterator.next();

        assertThat(values.get(0)).isEqualTo(2);
        assertThat(values.get(1)).isEqualTo(3);

        assertThat(iterator.hasNext()).isFalse();

        assertThatThrownBy(iterator::next)
            .isExactlyInstanceOf(NoSuchElementException.class);
    }
}
