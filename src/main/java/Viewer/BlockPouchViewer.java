package Viewer;

import GUI.GUI;
import Model.BlockPouch;

import static Viewer.ViewerConstants.*;

public class BlockPouchViewer {

    public void draw(BlockPouch blockPouch, GUI gui){

        gui.drawString(100, 65, "Block: ", DEFAULT_FOREGROUND_COLOR, DEFAULT_BACKGROUND_COLOR);
        gui.drawString(107, 65, blockPouch.getCurrentBlockName(), DEFAULT_FOREGROUND_COLOR, DEFAULT_BACKGROUND_COLOR);

        gui.drawString(100, 69, "Quantity: ", DEFAULT_FOREGROUND_COLOR, DEFAULT_BACKGROUND_COLOR);
        gui.drawString(110, 69, blockPouch.getCurrentBlockQuantity().toString(), DEFAULT_FOREGROUND_COLOR, DEFAULT_BACKGROUND_COLOR);
    }

}
