package com.lpoo.terrarius.viewer.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.arena.Arena;
import com.lpoo.terrarius.model.game.elements.Element;
import com.lpoo.terrarius.model.game.items.Toolbar;
import com.lpoo.terrarius.utils.Dimensions;
import com.lpoo.terrarius.viewer.image.ColoredImage;
import com.lpoo.terrarius.viewer.image.Image;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import static com.lpoo.terrarius.utils.GameConstants.STATUS_BAR_BACKGROUND_COLOR;
import static com.lpoo.terrarius.utils.GameConstants.STATUS_BAR_FOREGROUND_COLOR;

public class ToolbarViewer {
    private Image image;
    private final BlockPouchViewer blockPouchViewer = new BlockPouchViewer();

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
        int iconY = arena.getHeight() + (toolbar.getDimensions().getHeight() - toolbar.getToolbarCellLength()) / 2 + 3;

        return new Position(iconX, iconY);
    }

    public void draw(Toolbar toolbar, Dimensions arenaDimensions, GUI gui){
        Position toolbarPos = new Position(0, arenaDimensions.getHeight());

        this.image.draw(toolbarPos, Element.Orientation.RIGHT, gui);

        blockPouchViewer.draw(toolbar.getBlockPouch(), gui);

        String activeItemName = toolbar.getActiveItem().getComponentName();
        activeItemName = "Selected: " + activeItemName;

        gui.drawString(toolbarPos.getX() + 8, toolbarPos.getY() + 1, activeItemName,
                STATUS_BAR_FOREGROUND_COLOR, STATUS_BAR_BACKGROUND_COLOR);
    }
}
