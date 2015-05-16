package com.github.fge.multiterator;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public abstract class MultiteratorTest<M extends Multiterator<Integer>>
{
    private final Class<?> expectedClass;

    protected MultiteratorTest(final Class<?> expectedClass)
    {
        this.expectedClass = expectedClass;
    }

    protected abstract Multiterator<Integer> getMultiterator(
        final MultiteratorBuilder builder, final Stream<Integer> stream);

    @Test
    public final void classTest()
    {
        final Stream<Integer> stream = IntStream.range(0, 3).boxed();
        final MultiteratorBuilder builder = Multiterator.ofSize(2);
        final Multiterator<Integer> multiterator
            = getMultiterator(builder, stream);

        assertThat(multiterator).isExactlyInstanceOf(expectedClass);
    }

    @Test
    public void undersizedCollectionTest()
    {
        final Stream<Integer> stream
            = Collections.<Integer>emptyList().stream();

        try {
            getMultiterator(Multiterator.ofSize(1), stream);
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("size of collection (0) is lower than "
                + "requested window size (1)");
        }
    }

    @Test
    public void incorrectCollectionSizeForWindowTest()
    {
        final Stream<Integer> stream = IntStream.range(0, 5)
            .boxed();

        try {
            getMultiterator(Multiterator.ofSize(2).windowed(), stream);
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("size of collection (5) is not a multiple"
                + " of requested window size (2)");
        }
    }

    @Test
    public final void iterableShiftTest()
    {
        final Stream<Integer> stream = IntStream.range(0, 3).boxed();
        final MultiteratorBuilder builder = Multiterator.ofSize(2);
        final Multiterator<Integer> multiterator
            = getMultiterator(builder, stream);

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
    public final void streamShiftTest()
    {
        final Stream<Integer> stream = IntStream.range(0, 3).boxed();
        final MultiteratorBuilder builder = Multiterator.ofSize(2);
        final Multiterator<Integer> multiterator
            = getMultiterator(builder, stream);

        final List<Values<Integer>> list = multiterator.stream()
            .collect(Collectors.toList());

        assertThat(list).hasSize(2);

        final Iterator<Values<Integer>> iterator = list.iterator();

        Values<Integer> values;

        assertThat(iterator.hasNext()).isTrue();

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
    public final void iterableWindowedTest()
    {
        final Stream<Integer> stream = IntStream.range(0, 4).boxed();
        final MultiteratorBuilder builder = Multiterator.ofSize(2).windowed();
        final Multiterator<Integer> multiterator
            = getMultiterator(builder, stream);

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

    @Test
    public final void streamWindowedTest()
    {
        final Stream<Integer> stream = IntStream.range(0, 4).boxed();
        final MultiteratorBuilder builder = Multiterator.ofSize(2).windowed();
        final Multiterator<Integer> multiterator
            = getMultiterator(builder, stream);

        final List<Values<Integer>> list = multiterator.stream()
            .collect(Collectors.toList());

        assertThat(list).hasSize(2);

        final Iterator<Values<Integer>> iterator = list.iterator();

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
