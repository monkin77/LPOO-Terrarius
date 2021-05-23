package Terrarius.Model.elements.blocks;

import Terrarius.Model.Dimensions;
import Terrarius.Model.Position;

public class WoodBlock extends Block {
    private static final int WOOD_BLOCK_HARDNESS = 2;

    public WoodBlock(Position position) {
        super(position, new Dimensions(4, 4));
    }

    @Override
    protected int initHP() {
        return 20;
    }

    @Override
    public int getHardness() {
        return WOOD_BLOCK_HARDNESS;
    }
}
