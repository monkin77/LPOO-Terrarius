package Terrarius.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MouseHandlerTest {
    private MouseHandler mouseHandler;

    @BeforeEach
    public void setup() {
        mouseHandler = new MouseHandler();
    }

    @Test
    public void mousePressing() {
        Assertions.assertFalse(mouseHandler.isPressed());

        mouseHandler.mousePressed();
        Assertions.assertTrue(mouseHandler.isPressed());

        mouseHandler.mouseRelease();
        Assertions.assertFalse(mouseHandler.isPressed());
    }

    @Test
    public void mouseClicking() {
        Assertions.assertFalse(mouseHandler.isClick());

        mouseHandler.mousePressed();
        Assertions.assertTrue(mouseHandler.isClick());
        Assertions.assertFalse(mouseHandler.isClick());

        mouseHandler.mousePressed();
        mouseHandler.mouseRelease();
        Assertions.assertFalse(mouseHandler.isClick());
    }
}
