package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.BlockPouch;

import static Terrarius.Viewer.Game.GameViewerConstants.*;

public class BlockPouchViewer {

    public void draw(BlockPouch blockPouch, GUI gui){

        gui.drawString(100, 65, "Block: ", DEFAULT_FOREGROUND_COLOR, DEFAULT_BACKGROUND_COLOR);
        gui.drawString(107, 65, blockPouch.getCurrentBlockName(), DEFAULT_FOREGROUND_COLOR, DEFAULT_BACKGROUND_COLOR);

        gui.drawString(100, 69, "Quantity: ", DEFAULT_FOREGROUND_COLOR, DEFAULT_BACKGROUND_COLOR);
        gui.drawString(110, 69, blockPouch.getCurrentBlockQuantity().toString(), DEFAULT_FOREGROUND_COLOR, DEFAULT_BACKGROUND_COLOR);
    }

}
