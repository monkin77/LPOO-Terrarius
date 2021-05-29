package Terrarius.Model.Game.elements.blocks;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;

import static Terrarius.Utils.GameConstants.DEFAULT_BLOCK_DIMENSIONS;

public class DirtBlock extends Block {
    private static final int DIRT_BLOCK_HARDNESS = 1;

    public DirtBlock(Position position) {
        super(position, new Dimensions(DEFAULT_BLOCK_DIMENSIONS), "DirtBlock");
    }

    @Override
    protected int initHP() {
        return 10;
    }

    @Override
    public int getHardness() {
        return DIRT_BLOCK_HARDNESS;
    }

    @Override
    public String getComponentName() {
        return "Dirt";
    }
}
