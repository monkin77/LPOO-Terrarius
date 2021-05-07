package Model.elements.blocks;

import Model.Dimensions;
import Model.Position;

public class DirtBlock extends Block {
    protected static final int DIRT_BLOCK_HARDNESS = 1;

    public DirtBlock(Position position) {
        super(position, new Dimensions(4, 4));
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
