package Model.elements.blocks;

import Model.Position;

public class StoneBlock extends Block {
    protected static final int STONE_BLOCK_HARDNESS = 3;

    public StoneBlock(Position position) {
        super(position);
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
