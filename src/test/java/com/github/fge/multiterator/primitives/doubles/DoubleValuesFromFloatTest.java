package com.github.fge.multiterator.primitives.doubles;

import com.github.fge.multiterator.DoubleValues;
import com.github.fge.multiterator.primitives.doubles.fromfloats.DoubleValuesFromFloats;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.DoubleStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public final class DoubleValuesFromFloatTest
{
    @Test
    public void initialValuesTest()
    {
        final float[] array = { 0.0f, 1.0f, 2.0f, 3.0f };
        final DoubleValues values
            = new DoubleValuesFromFloats(array, array.length, 2);

        assertThat(values.get(0)).isEqualTo(0.0);
        assertThat(values.get(1)).isEqualTo(1.0);

        try {
            values.get(2);
            shouldHaveThrown(IndexOutOfBoundsException.class);
        } catch (IndexOutOfBoundsException ignored) {
        }

        DoubleStream stream;

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2L);

        stream = values.stream();

        final double[] expected = { 0.0, 1.0 };
        final double[] actual = stream.toArray();

        assertThat(Arrays.equals(actual, expected)).isTrue();
    }

    @Test
    public void shiftTest()
    {
        DoubleValuesFromFloats values;
        DoubleStream stream;
        double[] actual;
        double[] expected;


        final float[] array = { 0.0f, 1.0f, 2.0f, 3.0f };
        values = new DoubleValuesFromFloats(array, array.length, 2).shift();

        assertThat(values.get(0)).isEqualTo(1.0);
        assertThat(values.get(1)).isEqualTo(2.0);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2L);

        stream = values.stream();

        actual = stream.toArray();
        expected = new double[] { 1.0, 2.0 };
        assertThat(Arrays.equals(actual, expected)).isTrue();

        values = values.shift();

        assertThat(values.get(0)).isEqualTo(2.0);
        assertThat(values.get(1)).isEqualTo(3.0);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2L);

        stream = values.stream();

        actual = stream.toArray();
        expected = new double[] { 2.0, 3.0 };
        assertThat(Arrays.equals(actual, expected)).isTrue();
    }

    @Test
    public void windowedTest()
    {
        DoubleValuesFromFloats values;
        DoubleStream stream;
        double[] actual;
        double[] expected;

        final float[] array = { 0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f };
        values = new DoubleValuesFromFloats(array, array.length, 2)
            .nextWindow();

        assertThat(values.get(0)).isEqualTo(2.0);
        assertThat(values.get(1)).isEqualTo(3.0);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2L);

        stream = values.stream();

        actual = stream.toArray();
        expected = new double[] { 2.0, 3.0 };
        assertThat(Arrays.equals(actual, expected)).isTrue();

        values = values.nextWindow();

        assertThat(values.get(0)).isEqualTo(4.0);
        assertThat(values.get(1)).isEqualTo(5.0);

        stream = values.stream();

        assertThat(stream.count()).isEqualTo(2L);

        stream = values.stream();

        actual = stream.toArray();
        expected = new double[] { 4.0, 5.0 };
        assertThat(Arrays.equals(actual, expected)).isTrue();
    }
}
