package Terrarius.Viewer.Game;


import Terrarius.GUI.GUI;
import Terrarius.Model.Dimensions;
import Terrarius.Model.Position;
import Terrarius.Model.arena.Arena;
import Terrarius.Model.elements.Element;
import Terrarius.Model.items.Toolbar;
import Terrarius.Viewer.Image.Image;
import Terrarius.Viewer.Image.ColoredImage;

public class ToolbarViewer {
    private Image image;

    public ToolbarViewer() {
        this.image = new ColoredImage();
        this.image.load("Images/Toolbar/Toolbar.txt");
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Position calculateIconPosition(Arena arena, Toolbar toolbar, Integer iconIndex) {
        int toolbarCellsTotalWidth = toolbar.getToolbarCellLength() * toolbar.getMaxSlots() + toolbar.getToolbarSeparatorWidth() * (toolbar.getMaxSlots() + 1);

        int toolbarStartingPositionWidth = (toolbar.getDimensions().getWidth() - toolbarCellsTotalWidth) / 2 + 1;
        int iconIndexOffset = (iconIndex - 1) * (toolbar.getToolbarCellLength() + 1);

        int iconX = toolbarStartingPositionWidth + iconIndexOffset;
        int iconY = arena.getHeight() + (toolbar.getDimensions().getHeight() - toolbar.getToolbarCellLength()) / 2;


        return new Position(iconX, iconY);
    }

    public void draw(Toolbar toolbar, Dimensions arenaDimensions, GUI gui){
        Position toolbarPos = new Position(0, arenaDimensions.getHeight());

        this.image.draw(toolbarPos, Element.Orientation.RIGHT, gui);
    }
}
