package Model.arena;

import Model.Position;
import Model.elements.Hero;
import Model.elements.blocks.Block;
import Model.elements.blocks.DirtBlock;
import Model.elements.blocks.StoneBlock;
import Model.elements.blocks.WoodBlock;
import Model.elements.enemies.Enemy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


public class ArenaTest {
    Arena arena;

    @BeforeEach
    public void setUp() {
        this.arena = new Arena(10, 10);
    }

    @Test
    public void heroSetup() {
        Hero hero = new Hero(new Position(5, 5));
        this.arena.setHero(hero);
        Assertions.assertEquals(this.arena.getHero(), hero);
    }

    /*
    @Test
    public void enemiesSetup() {
        List<Enemy> enemies = new ArrayList<>();
        // Implement this test after the enemies creation
    }   */

    @Test
    public void blocksSetup() {
        List<Block> blocks = new ArrayList<>();

        blocks.add(new StoneBlock(new Position(0, 0)));
        blocks.add(new DirtBlock(new Position(0, 0)));
        blocks.add(new WoodBlock(new Position(0, 0)));

        this.arena.setBlocks(blocks);

        Assertions.assertEquals(this.arena.getBlocks(), blocks);
    }

    @Test
    public void isEmpty() {
        List<Block> blocks = new ArrayList<>();

        StoneBlock block1 = Mockito.mock(StoneBlock.class);
        Mockito.when(block1.getPosition()).thenReturn(new Position(5, 5));

        DirtBlock block2 = Mockito.mock(DirtBlock.class);
        Mockito.when(block2.getPosition()).thenReturn(new Position(10, 10));

        blocks.add(block1);
        blocks.add(block2);

        this.arena.setBlocks(blocks);

        Assertions.assertEquals(this.arena.isEmpty(new Position(0, 0)), true);

        Assertions.assertEquals(this.arena.isEmpty(new Position(5, 5)), false);
    }
}