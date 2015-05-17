package com.github.fge.multiterator.ints;

import com.github.fge.multiterator.IntMultiterator;
import com.github.fge.multiterator.IntValues;
import com.github.fge.multiterator.Multiterator;
import com.github.fge.multiterator.MultiteratorBuilder;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public abstract class IntMultiteratorTest
{
    private final Class<?> expectedClass;

    protected IntMultiteratorTest(final Class<?> expectedClass)
    {
        this.expectedClass = expectedClass;
    }

    protected abstract IntMultiterator getMultiterator(
        final MultiteratorBuilder builder, final IntStream stream);

    @Test
    public final void classTest()
    {
        final IntStream stream = IntStream.range(0, 3);
        final MultiteratorBuilder builder = Multiterator.ofSize(2);
        final IntMultiterator multiterator = getMultiterator(builder, stream);

        assertThat(multiterator).isExactlyInstanceOf(expectedClass);
    }

    @Test
    public void undersizedCollectionTest()
    {
        final IntStream stream = IntStream.of();

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
        final IntStream stream = IntStream.range(0, 5);

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
        final IntStream stream = IntStream.range(0, 3);
        final MultiteratorBuilder builder = Multiterator.ofSize(2);
        final IntMultiterator multiterator = getMultiterator(builder, stream);

        final List<IntValues> list = multiterator.stream()
            .collect(Collectors.toList());

        assertThat(list).hasSize(2);

        final Iterator<IntValues> iterator = list.iterator();

        IntValues values;

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
        final IntStream stream = IntStream.range(0, 4);
        final MultiteratorBuilder builder = Multiterator.ofSize(2).windowed();
        final IntMultiterator multiterator = getMultiterator(builder, stream);

        final List<IntValues> list = multiterator.stream()
            .collect(Collectors.toList());

        assertThat(list).hasSize(2);

        final Iterator<IntValues> iterator = list.iterator();

        IntValues values;

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
