package Model.elements.blocks;

import Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BlockTest {
    List<Block> blocks;

    @BeforeEach
    public void createBlocks() {
        blocks = new ArrayList<>();
        blocks.add(new DirtBlock(new Position(10, 10)));
        blocks.add(new WoodBlock(new Position(15, 15)));
        blocks.add(new StoneBlock(new Position(5, 5)));
    }

    @Test
    public void hp() {
        for (Block block : blocks) {
            Assertions.assertEquals(block.getHP(), block.initHP());
            block.setHp(5);
            Assertions.assertEquals(5, block.getHP());
        }
    }

    @Test
    public void hardness() {
        for (Block block : blocks) {
            if (block instanceof DirtBlock)
                Assertions.assertEquals(DirtBlock.DIRT_BLOCK_HARDNESS, block.getHardness());
            else if (block instanceof WoodBlock)
                Assertions.assertEquals(WoodBlock.WOOD_BLOCK_HARDNESS, block.getHardness());
            else if (block instanceof StoneBlock)
                Assertions.assertEquals(StoneBlock.STONE_BLOCK_HARDNESS, block.getHardness());
        }
    }
}
