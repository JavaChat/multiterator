package com.github.fge.multiterator;

import com.github.fge.multiterator.base.GenericValues;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public abstract class ValuesTest<V extends GenericValues<Integer, V>>
{
    protected abstract V getValues(Stream<Integer> stream, int windowSize);

    @SuppressWarnings("AutoBoxing")
    @Test
    public void initialValuesTest()
    {
        final V values = getValues(IntStream.range(0, 4).boxed(), 2);

        assertThat(values.get(0)).isEqualTo(0);
        assertThat(values.get(1)).isEqualTo(1);

        try {
            values.get(2);
            shouldHaveThrown(IndexOutOfBoundsException.class);
        } catch (IndexOutOfBoundsException ignored) {
        }

        Stream<Integer> stream;

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2);

        stream = values.stream();

        assertThat(stream.collect(Collectors.toList()))
            .hasSameElementsAs(Arrays.asList(0, 1));
    }

    @Test
    public void shiftTest()
    {
        V values;
        Stream<Integer> stream;

        values = getValues(IntStream.range(0, 4).boxed(), 2).shift();

        assertThat(values.get(0)).isEqualTo(1);
        assertThat(values.get(1)).isEqualTo(2);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2);

        stream = values.stream();

        assertThat(stream.collect(Collectors.toList()))
            .hasSameElementsAs(Arrays.asList(1, 2));

        values = values.shift();

        assertThat(values.get(0)).isEqualTo(2);
        assertThat(values.get(1)).isEqualTo(3);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2);

        stream = values.stream();

        assertThat(stream.collect(Collectors.toList()))
            .hasSameElementsAs(Arrays.asList(2, 3));
    }

    @Test
    public void windowedTest()
    {
        V values;
        Stream<Integer> stream;

        values = getValues(IntStream.range(0, 6).boxed(), 2).nextWindow();

        assertThat(values.get(0)).isEqualTo(2);
        assertThat(values.get(1)).isEqualTo(3);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2);

        stream = values.stream();

        assertThat(stream.collect(Collectors.toList()))
            .hasSameElementsAs(Arrays.asList(2, 3));

        values = values.nextWindow();

        assertThat(values.get(0)).isEqualTo(4);
        assertThat(values.get(1)).isEqualTo(5);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2);

        stream = values.stream();

        assertThat(stream.collect(Collectors.toList()))
            .hasSameElementsAs(Arrays.asList(4, 5));
    }
}
