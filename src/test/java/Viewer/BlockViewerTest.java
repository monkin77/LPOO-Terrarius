package Viewer;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Model.elements.blocks.Block;
import Viewer.Image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BlockViewerTest {
    private BlockViewer blockViewer;
    private Block block;
    private Image image;
    private GUI gui;

    @BeforeEach
    public void setup() {
        block = Mockito.mock(Block.class);
        Mockito.when(block.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(block.getOrientation()).thenReturn(Element.Orientation.RIGHT);

        image = Mockito.mock(Image.class);
        gui = Mockito.mock(GUI.class);

        blockViewer = new BlockViewer(block);
        blockViewer.setImage(image);
    }

    @Test
    public void update() {
        blockViewer.update();
        Mockito.verify(image, Mockito.times(1)).update();
    }

    @Test
    public void draw() {
        blockViewer.draw(block, gui);
        Mockito.verify(image, Mockito.times(1))
                .draw(block.getPosition(), block.getOrientation(), gui);
    }
}
