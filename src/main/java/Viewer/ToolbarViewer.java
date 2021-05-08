package Viewer;

import GUI.GUI;
import Model.Dimensions;
import Model.Position;
import Model.elements.Element;
import Model.items.Toolbar;
import Viewer.Image.Image;
import Viewer.Image.StillImage;

public class ToolbarViewer {
    protected Image image;

    public ToolbarViewer() {
        this.image = new StillImage();
        this.image.load("Images/Toolbar/Toolbar.txt");
    }

    public void draw(Toolbar toolbar, Dimensions arenaDimensions, GUI gui){
        Position toolbarPos = new Position(arenaDimensions.getWidth()/2 - toolbar.getDimensions().getWidth()/2, arenaDimensions.getHeight() - toolbar.getDimensions().getHeight());

        this.image.draw(toolbarPos, Element.Orientation.RIGHT,  gui);
    }
}
