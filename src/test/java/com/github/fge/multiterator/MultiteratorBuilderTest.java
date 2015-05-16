package com.github.fge.multiterator;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public class MultiteratorBuilderTest
{
    @Test
    public void windowSizeTest()
    {
        try {
            Multiterator.ofSize(0);
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("window size must be strictly positive");
        }
    }

    @Test
    public void undersizedCollectionTest()
    {
        try {
            Multiterator.ofSize(1).over(Collections.emptyList());
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("size of collection (0) is lower than "
                + "requested window size (1)");
        }
    }

    @Test
    public void incorrectCollectionSizeForWindowTest()
    {
        final List<Object> list = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> list.add(new Object()));

        try {
            Multiterator.ofSize(2).windowed().over(list);
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("size of collection (5) is not a multiple"
                + " of requested window size (2)");
        }
    }
}
