package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.BlockPouch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class BlockPouchViewerTest {
    BlockPouchViewer viewer;
    BlockPouch blockPouch;
    GUI gui;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);

        blockPouch = Mockito.mock(BlockPouch.class);
        Mockito.when(blockPouch.getCurrentBlockName()).thenReturn("Block");
        Mockito.when(blockPouch.getCurrentBlockQuantity()).thenReturn(2);

        viewer = new BlockPouchViewer();
    }

    @Test
    public void draw() throws IOException {
        viewer.draw(blockPouch, gui);

        Mockito.verify(gui, Mockito.times(4)).drawString(Mockito.anyInt(), Mockito.anyInt(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }
}
