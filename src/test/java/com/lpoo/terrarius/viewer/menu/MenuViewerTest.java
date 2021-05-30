package com.lpoo.terrarius.viewer.menu;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuViewerTest {
    private GUI gui;
    private Menu menu;
    private MenuViewer viewer;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);
        Mockito.when(gui.getWidth()).thenReturn(10);
        Mockito.when(gui.getHeight()).thenReturn(8);

        menu = Mockito.mock(Menu.class);
        Mockito.when(menu.getSelectedIndex()).thenReturn(0);
        Mockito.when(menu.getOption(Mockito.anyInt())).thenReturn("Option");
        Mockito.when(menu.isSelected(Mockito.anyInt())).thenReturn(false);
        Mockito.when(menu.getNumOptions()).thenReturn(3);

        viewer = new MenuViewer();
    }

    @Test
    public void draw() throws IOException {
        viewer.draw(gui, menu);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();

        Mockito.verify(gui, Mockito.times(80)).drawCharacter(Mockito.anyInt(), Mockito.anyInt(),
                Mockito.anyChar(), Mockito.anyString(), Mockito.anyString());

        Mockito.verify(gui, Mockito.times(4)).drawString(Mockito.anyInt(), Mockito.anyInt(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }
}
