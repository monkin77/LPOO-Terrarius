package Terrarius.Model.Game;

import Terrarius.Model.Game.elements.Block;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlockPouchTest {
    private BlockPouch blockPouch;

    @BeforeEach
    public void setup() {
        blockPouch = new BlockPouch();
    }

    @Test
    public void generateBlock() {
        String name = blockPouch.getCurrentBlockName();

        Block block = blockPouch.generateCurrentBlock(new Position(1, 1));

        Assertions.assertEquals(name, block.getComponentName());
    }

    @Test
    public void cycleBlocks() {
        String name = blockPouch.getCurrentBlockName();
        blockPouch.incrementBlock(blockPouch.generateCurrentBlock(new Position(1, 1)));
        int quant = blockPouch.getCurrentBlockQuantity();

        blockPouch.cycleCurrentBlock();
        Assertions.assertNotEquals(name, blockPouch.getCurrentBlockName());
        Assertions.assertNotEquals(quant, blockPouch.getCurrentBlockQuantity());
    }
}
