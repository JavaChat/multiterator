package com.github.fge.multiterator.list;

import com.github.fge.multiterator.Multiterator;
import com.github.fge.multiterator.Values;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public final class ListMultiteratorTest
{
    @Test
    public void shiftIterationTest()
    {
        final List<Integer> list = IntStream.rangeClosed(0, 2)
            .boxed()
            .collect(Collectors.toList());

        final Multiterator<Integer> multiterator = Multiterator.ofSize(2)
            .over(list);

        assertThat(multiterator).isInstanceOf(ListMultiterator.class);

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
        final List<Integer> list = IntStream.rangeClosed(0, 3)
            .boxed()
            .collect(Collectors.toList());

        final Multiterator<Integer> multiterator = Multiterator.ofSize(2)
            .windowed().over(list);

        assertThat(multiterator).isInstanceOf(ListMultiterator.class);

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
