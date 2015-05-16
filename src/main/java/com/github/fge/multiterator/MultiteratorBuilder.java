package com.github.fge.multiterator;

public final class MultiteratorBuilder
{
    private final int windowSize;
    private boolean windowed = false;

    MultiteratorBuilder(final int windowSize)
    {
        this.windowSize = windowSize;
    }

    public MultiteratorBuilder windowed()
    {
        windowed = true;
        return this;
    }

    public int getWindowSize()
    {
        return windowSize;
    }

    public boolean isWindowed()
    {
        return windowed;
    }
}
