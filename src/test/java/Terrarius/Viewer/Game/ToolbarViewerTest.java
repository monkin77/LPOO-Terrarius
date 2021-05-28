package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.items.Toolbar;
import Terrarius.Viewer.Image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ToolbarViewerTest {
    private ToolbarViewer toolbarViewer;
    private Toolbar toolbar;
    private Image image;
    private GUI gui;

    @BeforeEach
    public void setup() {
        image = Mockito.mock(Image.class);
        gui = Mockito.mock(GUI.class);

        toolbar = Mockito.mock(Toolbar.class);
        Mockito.when(toolbar.getDimensions()).thenReturn(new Dimensions(10, 10));

        toolbarViewer = new ToolbarViewer();
        toolbarViewer.setImage(image);
    }

    @Test
    public void draw() {
        /*
        toolbarViewer.draw(toolbar, new Dimensions(50, 50), gui);

        Mockito.verify(image, Mockito.times(1))
                .draw(new Position(0, 50), Element.Orientation.RIGHT, gui);
                // TODO remake test and possible change class. Can't control BlockPouchViewer
         */
    }
}
