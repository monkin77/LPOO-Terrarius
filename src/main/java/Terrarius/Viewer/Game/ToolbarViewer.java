package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.items.Toolbar;
import Terrarius.Viewer.Image.Image;
import Terrarius.Viewer.Image.ColoredImage;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ToolbarViewer {
    private Image image;
    private final BlockPouchViewer blockPouchViewer = new BlockPouchViewer(); //TODO probably not a good idea to stay here, but this branch is focused more on the functionality than on the "beaty"

    public ToolbarViewer() {
        this.image = new ColoredImage();
        try {
            this.image.load("Images/Toolbar/Toolbar.txt");
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
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

        blockPouchViewer.draw(toolbar.getBlockPouch(), gui);
    }
}
