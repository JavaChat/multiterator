package com.github.fge.multiterator.ints;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public final class IntValuesImplTest
{
    @Test
    public void initialValuesTest()
    {
        final int[] array = IntStream.range(0, 4).toArray();
        final IntValues values = new IntValuesImpl(array, array.length, 2);

        assertThat(values.get(0)).isEqualTo(0);
        assertThat(values.get(1)).isEqualTo(1);

        try {
            values.get(2);
            shouldHaveThrown(IndexOutOfBoundsException.class);
        } catch (IndexOutOfBoundsException ignored) {
        }

        IntStream stream;

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2);

        stream = values.stream();

        final int[] expected = { 0, 1 };
        final int[] actual = stream.toArray();

        assertThat(Arrays.equals(actual, expected)).isTrue();
    }

    @Test
    public void shiftTest()
    {
        IntValuesImpl values;
        IntStream stream;
        int[] actual;
        int[] expected;


        final int[] array = IntStream.range(0, 4).toArray();
        values = new IntValuesImpl(array, array.length, 2).shift();

        assertThat(values.get(0)).isEqualTo(1);
        assertThat(values.get(1)).isEqualTo(2);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2);

        stream = values.stream();

        actual = stream.toArray();
        expected = new int[] { 1, 2 };
        assertThat(Arrays.equals(actual, expected)).isTrue();

        values = values.shift();

        assertThat(values.get(0)).isEqualTo(2);
        assertThat(values.get(1)).isEqualTo(3);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2);

        stream = values.stream();

        actual = stream.toArray();
        expected = new int[] { 2, 3 };
        assertThat(Arrays.equals(actual, expected)).isTrue();
    }

    @Test
    public void windowedTest()
    {
        IntValuesImpl values;
        IntStream stream;
        int[] actual;
        int[] expected;

        final int[] array = IntStream.range(0, 6).toArray();
        values = new IntValuesImpl(array, array.length, 2).nextWindow();

        assertThat(values.get(0)).isEqualTo(2);
        assertThat(values.get(1)).isEqualTo(3);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2);

        stream = values.stream();

        actual = stream.toArray();
        expected = new int[] { 2, 3 };
        assertThat(Arrays.equals(actual, expected)).isTrue();

        values = values.nextWindow();

        assertThat(values.get(0)).isEqualTo(4);
        assertThat(values.get(1)).isEqualTo(5);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2);

        stream = values.stream();

        actual = stream.toArray();
        expected = new int[] { 4, 5 };
        assertThat(Arrays.equals(actual, expected)).isTrue();
    }
}
