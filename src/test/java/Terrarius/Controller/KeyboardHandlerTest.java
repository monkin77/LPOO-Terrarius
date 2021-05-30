package Terrarius.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class KeyboardHandlerTest {
    private KeyboardHandler keyboardHandler;
    private List<Integer> keyCodes;

    @BeforeEach
    public void setup() {
        keyboardHandler = new KeyboardHandler();

        keyCodes = Arrays.asList(VK_ESCAPE, VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT);
    }

    @Test
    public void beforePressing() {
        for (Integer code : keyCodes)
            Assertions.assertFalse(keyboardHandler.isKeyPressed(code));
    }

    @Test
    public void pressKey() {
        keyboardHandler.pressKey(VK_UP);
        Assertions.assertTrue(keyboardHandler.isKeyPressed(VK_UP));

        Assertions.assertFalse(keyboardHandler.isKeyPressed(VK_ESCAPE));
        Assertions.assertFalse(keyboardHandler.isKeyPressed(VK_DOWN));
    }

    @Test
    public void releaseKey() {
        keyboardHandler.pressKey(VK_DOWN);
        Assertions.assertTrue(keyboardHandler.isKeyPressed(VK_DOWN));

        keyboardHandler.releaseKey(VK_DOWN);
        Assertions.assertFalse(keyboardHandler.isKeyPressed(VK_DOWN));
    }

    @Test
    public void readKey() {
        keyboardHandler.pressKey(VK_DOWN);
        Assertions.assertTrue(keyboardHandler.readKey(VK_DOWN));
        Assertions.assertFalse(keyboardHandler.readKey(VK_DOWN));
    }
}
