package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.BlockPouch;

import static Terrarius.Viewer.Game.GameViewerConstants.*;

public class BlockPouchViewer {

    public void draw(BlockPouch blockPouch, GUI gui){

        gui.drawString(100, 68, "Block: ", DEFAULT_FOREGROUND_COLOR, STATUS_BAR_BACKGROUND_COLOR);
        gui.drawString(107, 68, blockPouch.getCurrentBlockName(),
                        DEFAULT_FOREGROUND_COLOR, STATUS_BAR_BACKGROUND_COLOR);

        gui.drawString(100, 72, "Quantity: ", DEFAULT_FOREGROUND_COLOR, STATUS_BAR_BACKGROUND_COLOR);
        gui.drawString(110, 72, blockPouch.getCurrentBlockQuantity().toString(),
                    DEFAULT_FOREGROUND_COLOR, STATUS_BAR_BACKGROUND_COLOR);
    }

}
