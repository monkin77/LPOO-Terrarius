package Model.arena;

import Model.Dimensions;
import Model.Position;
import Model.elements.blocks.Block;
import Model.elements.blocks.DirtBlock;
import Model.elements.blocks.StoneBlock;
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

        List<Block> blocks = new ArrayList<>();

        StoneBlock block1 = Mockito.mock(StoneBlock.class);
        Mockito.when(block1.getPosition()).thenReturn(new Position(5, 5));
        Mockito.when(block1.getDimensions()).thenReturn(new Dimensions(4, 4));

        DirtBlock block2 = Mockito.mock(DirtBlock.class);
        Mockito.when(block2.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(block2.getDimensions()).thenReturn(new Dimensions(4, 4));

        blocks.add(block1);
        blocks.add(block2);

        this.arena.setBlocks(blocks);
    }

    @Test
    public void isEmpty() {
        Assertions.assertTrue(this.arena.isEmpty(new Position(0, 0)));

        Assertions.assertFalse(this.arena.isEmpty(new Position(5, 5)));
    }

    @Test
    public void overlappedCollisions() {
        Assertions.assertTrue(this.arena.collides(new Position(4, 4), new Dimensions(2, 2)));
        Assertions.assertTrue(this.arena.collides(new Position(11, 11), new Dimensions(5, 5)));
    }

    @Test
    public void embeddedCollisions() {
        Assertions.assertTrue(this.arena.collides(new Position(9, 9), new Dimensions(6, 2)));
        Assertions.assertTrue(this.arena.collides(new Position(6, 6), new Dimensions(1, 1)));
    }

    @Test
    public void notColliding() {
        Assertions.assertFalse(this.arena.collides(new Position(4, 4), new Dimensions(1, 1)));
        Assertions.assertFalse(this.arena.collides(new Position(14, 14), new Dimensions(5, 5)));
    }

    @Test
    public void hasAdjacent() {
        // Top right
        Assertions.assertTrue(this.arena.hasAdjacent(new Position(4, 6), new Dimensions(1, 1)));

        // Bottom right
        Assertions.assertTrue(this.arena.hasAdjacent(new Position(3, 8), new Dimensions(2, 2)));

        // Top Left
        Assertions.assertTrue(this.arena.hasAdjacent(new Position(9, 6), new Dimensions(1, 1)));

        // Bottom Left
        Assertions.assertTrue(this.arena.hasAdjacent(new Position(9, 8), new Dimensions(2, 2)));

    }

    @Test
    public void notAdjacent() {
        // Far
        Assertions.assertFalse(this.arena.hasAdjacent(new Position(1, 1), new Dimensions(2, 2)));

        // Adjacent but larger
        Assertions.assertFalse(this.arena.hasAdjacent(new Position(4, 4), new Dimensions(1, 10)));

        // Adjacent but taller
        Assertions.assertFalse(this.arena.hasAdjacent(new Position(4, 4), new Dimensions(10, 1)));

        // Adjacent but larger and taller
        Assertions.assertFalse(this.arena.hasAdjacent(new Position(4, 4), new Dimensions(10, 10)));

        // Embedded
        Assertions.assertFalse(this.arena.hasAdjacent(new Position(8, 6), new Dimensions(1, 1)));
    }
}
