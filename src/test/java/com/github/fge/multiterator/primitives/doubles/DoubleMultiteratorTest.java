package com.github.fge.multiterator.primitives.doubles;

import com.github.fge.multiterator.DoubleMultiterator;
import com.github.fge.multiterator.DoubleValues;
import com.github.fge.multiterator.Multiterator;
import com.github.fge.multiterator.MultiteratorBuilder;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public abstract class DoubleMultiteratorTest
{
    private final Class<?> expectedClass;

    protected DoubleMultiteratorTest(final Class<?> expectedClass)
    {
        this.expectedClass = expectedClass;
    }

    protected abstract DoubleMultiterator getMultiterator(
        final MultiteratorBuilder builder, final DoubleStream stream);

    @Test
    public final void classTest()
    {
        final DoubleStream stream = IntStream.range(0, 3)
            .mapToDouble(i -> i);
        final MultiteratorBuilder builder = Multiterator.ofSize(2);
        final DoubleMultiterator multiterator
            = getMultiterator(builder, stream);

        assertThat(multiterator).isExactlyInstanceOf(expectedClass);
    }

    @Test
    public void undersizedCollectionTest()
    {
        final DoubleStream stream = DoubleStream.of();

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
        final DoubleStream stream = IntStream.range(0, 5)
            .mapToDouble(i -> i);

        try {
            getMultiterator(Multiterator.ofSize(2).windowed(), stream);
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("size of collection (5) is not a multiple"
                + " of requested window size (2)");
        }
    }

    @Test
    public final void streamShiftTest()
    {
        final DoubleStream stream = IntStream.range(0, 3)
            .mapToDouble(i -> i);
        final MultiteratorBuilder builder = Multiterator.ofSize(2);
        final DoubleMultiterator multiterator
            = getMultiterator(builder, stream);

        final List<DoubleValues> list = multiterator.stream()
            .collect(Collectors.toList());

        assertThat(list).hasSize(2);

        final Iterator<DoubleValues> iterator = list.iterator();

        DoubleValues values;

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
    public final void streamWindowedTest()
    {
        final DoubleStream stream = IntStream.range(0, 4).mapToDouble(i -> i);
        final MultiteratorBuilder builder = Multiterator.ofSize(2).windowed();
        final DoubleMultiterator multiterator
            = getMultiterator(builder, stream);

        final List<DoubleValues> list = multiterator.stream()
            .collect(Collectors.toList());

        assertThat(list).hasSize(2);

        final Iterator<DoubleValues> iterator = list.iterator();

        DoubleValues values;

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
