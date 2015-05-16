package com.github.fge.multiterator;

import org.testng.annotations.Test;

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
}
