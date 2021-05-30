package com.lpoo.terrarius.viewer.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.Element;
import com.lpoo.terrarius.model.game.elements.Block;
import com.lpoo.terrarius.utils.Dimensions;
import com.lpoo.terrarius.viewer.image.ColoredImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BlockViewerTest {
    private BlockViewer blockViewer;
    private Block block;
    private ColoredImage image;
    private GUI gui;

    @BeforeEach
    public void setup() {
        block = Mockito.mock(Block.class);
        Mockito.when(block.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(block.getOrientation()).thenReturn(Element.Orientation.RIGHT);
        Mockito.when(block.getComponentName()).thenReturn("WoodBlock");

        gui = Mockito.mock(GUI.class);

        blockViewer = new BlockViewer(block);

        image = Mockito.spy( blockViewer.getImage());
        blockViewer.setImage(image);
    }

    @Test
    public void draw() {
        blockViewer.draw(block, gui);
        Mockito.verify(this.image, Mockito.times(1))
                .draw(block.getPosition(), block.getOrientation(), gui);
    }

    @Test
    public void createImage() {
        Assertions.assertEquals(new Dimensions(4, 4), this.blockViewer.getImage().getDimensions() );
    }
}
