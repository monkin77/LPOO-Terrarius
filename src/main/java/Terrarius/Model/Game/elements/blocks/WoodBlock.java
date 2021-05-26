package Terrarius.Model.Game.elements.blocks;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;

public class WoodBlock extends Block {
    private static final int WOOD_BLOCK_HARDNESS = 2;

    public WoodBlock(Position position) {
        super(position, new Dimensions(4, 4), "WoodBlock");
    }

    @Override
    protected int initHP() {
        return 200;
    }

    @Override
    public int getHardness() {
        return WOOD_BLOCK_HARDNESS;
    }

    @Override
    public String getComponentName() {
        return "Wood";
    }
}
