package com.lpoo.terrarius.utils;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;

public class DimensionsTest {
    @Property
    public void incrementWidth(@ForAll @IntRange(min=1, max=10000) int a,
                                @ForAll @IntRange(min=-10000, max=10000) int w,
                                @ForAll @IntRange(max=100) int b) {

        Dimensions dimensions = new Dimensions(1, a);
        dimensions.incrementWidth(w);

        Assertions.assertTrue(dimensions.getWidth() > 0);

        if (w + a <= 0) return;

        Assertions.assertEquals(a + w, dimensions.getWidth());

        dimensions.incrementWidth(-w);
        Assertions.assertEquals(a, dimensions.getWidth());

        if (w < 0) return;

        for (int i = 0; i < b; ++i)
            dimensions.incrementWidth(w);

        Assertions.assertEquals(a + b * w, dimensions.getWidth());

    }

    @Property
    public void incrementHeight(@ForAll @IntRange(min=1, max=10000) int a,
                                @ForAll @IntRange(min=-10000, max=10000) int h,
                                @ForAll @IntRange(max=100) int b) {

        Dimensions dimensions = new Dimensions(a, 1);
        dimensions.incrementHeight(h);

        Assertions.assertTrue(dimensions.getHeight() > 0);

        if (h + a <= 0) return;

        Assertions.assertEquals(a + h, dimensions.getHeight());

        dimensions.incrementHeight(-h);
        Assertions.assertEquals(a, dimensions.getHeight());

        if (h < 0) return;

        for (int i = 0; i < b; ++i)
            dimensions.incrementHeight(h);

        Assertions.assertEquals(a + b * h, dimensions.getHeight());

    }
}
