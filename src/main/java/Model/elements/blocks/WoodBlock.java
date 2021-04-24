package Model.elements.blocks;

import Model.Position;

public class WoodBlock extends Block {
    private static final int WOOD_BLOCK_HARDNESS = 2;

    public WoodBlock(Position position) {
        super(position);
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
