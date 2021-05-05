package Viewer;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Model.elements.Hero;
import Model.items.Item;
import Model.items.Toolbar;
import Viewer.Image.Image;
import Viewer.Image.StillImage;

public class ToolbarViewer {
    protected Image image;

    public ToolbarViewer() {
        this.image = new StillImage();
        this.image.load("Images/Toolbar.txt");
    }

    public void draw(Toolbar toolbar, GUI gui){
        Position toolbarPos = new Position(64 - 20, 59);    // When toolbar has the Dimensions attribute replace these random values

        this.image.draw(toolbarPos, gui);
    }
    /* NEXT STEP: IMPROVE THE DRAW TO DRAW ICONS OF THE TOOLS */
}
