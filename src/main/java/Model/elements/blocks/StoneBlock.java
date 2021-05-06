package Model.elements.blocks;

import Model.Dimensions;
import Model.Position;

public class StoneBlock extends Block {
    protected static final int STONE_BLOCK_HARDNESS = 3;

    public StoneBlock(Position position) {
        super(position, new Dimensions(4, 4));
    }

    @Override
    protected int initHP() {
        return 30;
    }

    @Override
    public int getHardness() {
        return STONE_BLOCK_HARDNESS;
    }
}
