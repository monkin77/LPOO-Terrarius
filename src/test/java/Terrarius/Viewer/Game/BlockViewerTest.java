package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.Block;
import Terrarius.Utils.Dimensions;
import Terrarius.Viewer.Image.ColoredImage;
import Terrarius.Viewer.Image.Image;
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
