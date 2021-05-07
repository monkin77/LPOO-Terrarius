package Viewer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FrameHandlerTest {
    private FrameHandler frameHandler;

    @BeforeEach
    public void setup() {
        frameHandler = new FrameHandler(0, 5, 0, 10);
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
}
