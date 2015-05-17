package com.github.fge.multiterator;

import com.github.fge.multiterator.array.ArrayValues;
import com.github.fge.multiterator.collection.CollectionValues;
import com.github.fge.multiterator.list.ListValues;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public final class EqualsHashcodeTostringTest
{
    private static final List<String> LIST = Arrays.asList(
        "one", "two", "three", "four", "five",
        "six", "seven", "eight", "nine", "ten"
    );

    @Test
    public void equalsHashcodeTest()
    {
        final int size = 10;
        final Values<String> fromList = new ListValues<>(LIST, size, size);
        final Values<String> fromCollection
            = new CollectionValues<>(LIST.iterator(), size, size);
        final String[] array = LIST.toArray(new String[size]);
        final Values<String> fromArray = new ArrayValues<>(array, size, size);

        final List<Values<?>[]> tested = Arrays.asList(
            new Values<?>[] { fromList, fromCollection },
            new Values<?>[] { fromCollection, fromArray },
            new Values<?>[] { fromArray, fromList }
        );

        try (
            final AutoCloseableSoftAssertions soft
                = new AutoCloseableSoftAssertions();
        ) {
            tested.stream().forEach(values -> {
                final Values<?> first = values[0];
                final Values<?> second = values[1];
                soft.assertThat(first.hashCode())
                    .as("hash code")
                    .isEqualTo(second.hashCode());
                soft.assertThat(first.equals(second))
                    .as("equals first -> second")
                    .isTrue();
                soft.assertThat(second.equals(first))
                    .as("equals second -> first")
                    .isTrue();
                soft.assertThat(first.equals(null))
                    .as("!first.equals(null)")
                    .isFalse();
                soft.assertThat(second.equals(null))
                    .as("!second.equals(null)")
                    .isFalse();
            });
        }
    }

    @Test
    public void toStringTest()
    {
        final int size = 10;
        final Values<String> values = new ListValues<>(LIST, size, 3);

        assertThat(values.toString()).isEqualTo("<one, two, three>");
    }
}
