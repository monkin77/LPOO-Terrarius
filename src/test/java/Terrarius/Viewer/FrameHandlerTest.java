package Terrarius.Viewer;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class FrameHandlerTest {
    private FrameHandler frameHandler;

    @BeforeEach
    public void setup() {
        frameHandler = new FrameHandler(0, 5, 0, 10);
    }

    @Test
    public void defaultCreation() {
        FrameHandler defaultFrame = new FrameHandler();
        // Prevent division by 0
        Assertions.assertNotEquals(0, defaultFrame.getTotalFPI());
        Assertions.assertNotEquals(0, defaultFrame.getTotalImages());
    }

    @Test
    public void update() {
        frameHandler.update();

        Assertions.assertEquals(1, frameHandler.getCurrentFPI());
        Assertions.assertEquals(0, frameHandler.getCurrentImage());

        for (int i = 1; i < frameHandler.getTotalFPI(); ++i)
            frameHandler.update();

        Assertions.assertEquals(0, frameHandler.getCurrentFPI());
        Assertions.assertEquals(1, frameHandler.getCurrentImage());

        Assertions.assertEquals(5, frameHandler.getTotalImages());
        Assertions.assertEquals(10, frameHandler.getTotalFPI());
    }

    @Test
    public void reset() {
        frameHandler.setCurrentFPI(9);
        frameHandler.setCurrentImage(4);
        frameHandler.reset();

        Assertions.assertEquals(0, frameHandler.getCurrentFPI());
        Assertions.assertEquals(0, frameHandler.getCurrentImage());
    }

    @Property
    public void testAttributesLimits(@ForAll("numIterations") Integer iter, @ForAll @Positive Integer totalImages, @ForAll @Positive Integer totalFPI) {
        FrameHandler frameHandler = new FrameHandler(0, totalImages, 0, totalFPI);

        for(int i = 0; i < iter; i++)
            frameHandler.update();

        Assertions.assertTrue(frameHandler.getCurrentFPI() <= frameHandler.getTotalFPI());
        Assertions.assertTrue(frameHandler.getCurrentImage() <= frameHandler.getTotalImages());
        Assertions.assertTrue(frameHandler.getCurrentImage() >= 0);
        Assertions.assertTrue(frameHandler.getCurrentFPI() >= 0);
    }

    @Property(tries = 2000)
    public void testResultImage(@ForAll("numIterations") Integer iter, @ForAll @Positive Integer totalImages, @ForAll @Positive Integer totalFPI) {
        FrameHandler frameHandler = new FrameHandler(0, totalImages, 0, totalFPI);

        for(int i = 0; i < iter; i++)
            frameHandler.update();

        int numImageChanges = iter / totalFPI;
        int resultImage = numImageChanges % totalImages;
        int resultFPI = iter % totalFPI;

        Assertions.assertEquals(resultImage, frameHandler.getCurrentImage());
        Assertions.assertEquals(resultFPI, frameHandler.getCurrentFPI());
    }

    @Provide
    Arbitrary<Integer> numIterations() {
        return Arbitraries.integers().between(1, 10000);
    }
}
