package com.github.fge.multiterator.ints;

import com.github.fge.multiterator.ints.asis.IntValuesAsIs;
import com.github.fge.multiterator.ints.fromchars.IntValuesFromChars;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public final class IntValuesTest
{
    @Test
    public void equalsHashcodeTest()
    {
        final int[] ints = IntStream.range(0, 5).toArray();
        final char[] chars = { 0, 1, 2, 3, 4, 5 };

        final IntValues asIs = new IntValuesAsIs(ints, 6, 4);
        final IntValues fromChars = new IntValuesFromChars(chars, 6, 4);

        try (
            final AutoCloseableSoftAssertions soft
                = new AutoCloseableSoftAssertions();
        ) {
            soft.assertThat(asIs.hashCode()).as("hashcodes are equal")
                .isEqualTo(fromChars.hashCode());
            assertThat(asIs).as("asIs.equals(fromChars)")
                .isEqualTo(fromChars);
            assertThat(fromChars).as("fromChars.equals(asIs)")
                .isEqualTo(asIs);
            assertThat(asIs.equals(null)).as("asIs and null")
                .isFalse();
            assertThat(fromChars.equals(null)).as("fromChars and null")
                .isFalse();
        }
    }
}
