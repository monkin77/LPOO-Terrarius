package Terrarius.Model.Game.elements.blocks;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;

import static Terrarius.Utils.GameConstants.DEFAULT_BLOCK_DIMENSIONS;

public class WoodBlock extends Block {
    private static final int WOOD_BLOCK_HARDNESS = 2;

    public WoodBlock(Position position) {
        super(position, new Dimensions(DEFAULT_BLOCK_DIMENSIONS), "WoodBlock");
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
