package com.github.fge.multiterator.primitives.doubles;

import com.github.fge.multiterator.DoubleValues;
import com.github.fge.multiterator.primitives.doubles.direct.DirectDoubleValues;
import com.github.fge.multiterator.primitives.doubles.fromfloats.DoubleValuesFromFloats;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public final class DoubleValuesTest
{
    @Test
    public void equalsHashcodeTest()
    {
        final double[] doubles = IntStream.range(0, 5)
            .mapToDouble(i -> i).toArray();
        final float[] floats = { 0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f };

        final DoubleValues direct  = new DirectDoubleValues(doubles, 6, 4);
        final DoubleValues fromFloats
            = new DoubleValuesFromFloats(floats, 6, 4);

        try (
            final AutoCloseableSoftAssertions soft
                = new AutoCloseableSoftAssertions();
        ) {
            soft.assertThat(direct.hashCode()).as("hashcodes are equal")
                .isEqualTo(fromFloats.hashCode());
            assertThat(direct).as("asIs.equals(fromChars)")
                .isEqualTo(fromFloats);
            assertThat(fromFloats).as("fromChars.equals(asIs)")
                .isEqualTo(direct);
            assertThat(direct.equals(null)).as("asIs and null")
                .isFalse();
            assertThat(fromFloats.equals(null)).as("fromChars and null")
                .isFalse();
        }
    }
}
