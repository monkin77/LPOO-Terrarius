package com.lpoo.terrarius.gui;

import com.lpoo.terrarius.controller.KeyboardHandler;
import com.lpoo.terrarius.controller.MouseHandler;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.lpoo.terrarius.utils.GameConstants.DEFAULT_BACKGROUND_COLOR;
import static com.lpoo.terrarius.utils.GameConstants.DEFAULT_FOREGROUND_COLOR;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;

public class LanternaGuiTest {
    private LanternaGui gui;
    private TerminalScreen screen;
    private TextGraphics textGraphics;
    private KeyboardHandler keyboardHandler;
    private MouseHandler mouseHandler;

    @BeforeEach
    public void setup() {
        screen = Mockito.mock(TerminalScreen.class);
        textGraphics = Mockito.mock(TextGraphics.class);
        keyboardHandler = Mockito.mock(KeyboardHandler.class);
        mouseHandler = Mockito.mock(MouseHandler.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);

        Mockito.when(keyboardHandler.isKeyPressed(Mockito.anyInt())).thenReturn(false);
        Mockito.when(mouseHandler.isPressed()).thenReturn(false);
        Mockito.when(mouseHandler.isClick()).thenReturn(false);
        Mockito.when(keyboardHandler.isKeyPressed(VK_UP)).thenReturn(true);
        Mockito.when(keyboardHandler.isKeyPressed(VK_RIGHT)).thenReturn(true);

        gui = new LanternaGui(screen, keyboardHandler, mouseHandler);
    }

    @Test
    public void drawDefaultCharacter() {
        gui.drawCharacter(10, 10, 'c');

        Mockito.verify(textGraphics, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString(DEFAULT_FOREGROUND_COLOR));

        Mockito.verify(textGraphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString(DEFAULT_BACKGROUND_COLOR));

        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(10, 10, 'c');
    }

    @Test
    public void drawColouredCharacter() {
        gui.drawCharacter(10, 10, 'c', "blue", "blue");

        Mockito.verify(textGraphics, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("blue"));

        Mockito.verify(textGraphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("blue"));

        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(10, 10, 'c');
    }

    @Test
    public void drawString() {
        gui.drawString(10, 10, "Grande Restivo", "red", "red");

        Mockito.verify(textGraphics, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("red"));

        Mockito.verify(textGraphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("red"));

        Mockito.verify(textGraphics, Mockito.times(1)).putString(10, 10, "Grande Restivo");
    }

    @Test
    public void clear() {
        gui.clear();
        Mockito.verify(screen, Mockito.times(1)).clear();
    }

    @Test
    public void refresh() throws IOException {
        gui.refresh();
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    public void close() throws IOException {
        gui.close();
        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    public void getNextActions() {
        Mockito.when(mouseHandler.isClick()).thenReturn(true);

        List<GUI.ACTION> actionList = gui.getNextActions();
        List<GUI.ACTION> expected = Arrays.asList(GUI.ACTION.CLICK, GUI.ACTION.UP, GUI.ACTION.RIGHT);

        Assertions.assertIterableEquals(expected, actionList);
    }
}
