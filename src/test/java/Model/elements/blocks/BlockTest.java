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
    public void initHP() {
        for (Block block : blocks) {
            Assertions.assertEquals(block.getHP(), block.initHP());
            int oldHP = block.getHP();
            block.setHp(0);
            Assertions.assertEquals(oldHP, block.initHP());
        }
    }

    @Test
    public void DirtBlock() {
        DirtBlock dirt = (DirtBlock) blocks.get(0);
        Assertions.assertEquals(10, dirt.getHP());
        Assertions.assertEquals(1, dirt.getHardness());
    }

    @Test
    public void WoodBlock() {
        WoodBlock wood = (WoodBlock) blocks.get(1);
        Assertions.assertEquals(20, wood.getHP());
        Assertions.assertEquals(2, wood.getHardness());
    }

    @Test
    public void StoneBlock() {
        StoneBlock stone = (StoneBlock) blocks.get(2);
        Assertions.assertEquals(30, stone.getHP());
        Assertions.assertEquals(3, stone.getHardness());
    }
}
