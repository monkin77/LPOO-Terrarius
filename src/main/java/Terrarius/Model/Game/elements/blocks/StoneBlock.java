package Terrarius.Model.Game.elements.blocks;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;

import static Terrarius.Utils.GameConstants.DEFAULT_BLOCK_DIMENSIONS;

public class StoneBlock extends Block {
    private static final int STONE_BLOCK_HARDNESS = 3;

    public StoneBlock(Position position) {
        super(position, new Dimensions(DEFAULT_BLOCK_DIMENSIONS), "StoneBlock");
    }

    @Override
    protected int initHP() {
        return 30;
    }

    @Override
    public int getHardness() {
        return STONE_BLOCK_HARDNESS;
    }

    @Override
    public String getComponentName() {
        return "Stone";
    }
}
