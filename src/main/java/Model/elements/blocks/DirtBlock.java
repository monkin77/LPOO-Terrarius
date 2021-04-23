package Model.elements.blocks;

import Model.Position;

public class DirtBlock extends Block {
    private static final int DIRT_BLOCK_HARDNESS = 1;

    public DirtBlock(Position position) {
        super(position);
    }

    @Override
    protected int initHP() {
        return 10;
    }

    @Override
    public int getHardness() {
        return DIRT_BLOCK_HARDNESS;
    }
}
