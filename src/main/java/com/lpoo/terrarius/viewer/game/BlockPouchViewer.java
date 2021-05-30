package com.lpoo.terrarius.viewer.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.BlockPouch;

import static com.lpoo.terrarius.utils.GameConstants.DEFAULT_FOREGROUND_COLOR;
import static com.lpoo.terrarius.utils.GameConstants.STATUS_BAR_BACKGROUND_COLOR;

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
