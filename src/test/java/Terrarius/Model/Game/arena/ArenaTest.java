package Terrarius.Model.Game.arena;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Block;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static Terrarius.Utils.GameConstants.SCREEN_WIDTH;


public class ArenaTest {
    Arena arena;

    @BeforeEach
    public void setUp() throws FileNotFoundException, URISyntaxException {

        arena = new Arena();

    }

    @Test
    public void boundaries(){

        this.arena.getHero().setPosition(new Position(-1, 10));
        Assertions.assertEquals(Arena.BOUNDARY.MAP_LEFT,arena.checkMapZoneAndUpdate());

        this.arena.getHero().setPosition(new Position(128, 10));
        Assertions.assertEquals(Arena.BOUNDARY.MAP_RIGHT,arena.checkMapZoneAndUpdate());

        this.arena.getHero().setPosition(new Position(0, 10));
        Assertions.assertEquals(Arena.BOUNDARY.MAP_SAME,arena.checkMapZoneAndUpdate());

        this.arena.getHero().setPosition(new Position(SCREEN_WIDTH - arena.getHero().getDimensions().getWidth()-1, 10));
        Assertions.assertEquals(Arena.BOUNDARY.MAP_SAME,arena.checkMapZoneAndUpdate());

    }

    @Test
    public void adjacent(){

        Block block = arena.getBlocks().get(new Random().nextInt(arena.getBlocks().size()));

        Position position1 = new Position(
                block.getPosition().getX() + block.getDimensions().getWidth(),
                block.getPosition().getY());
        Dimensions dimensions1 = new Dimensions(4, 4);

        Assertions.assertTrue(arena.hasAdjacentBlock(position1, dimensions1));

        Position position2 = new Position(
                block.getPosition().getX() + block.getDimensions().getWidth() - 1,
                block.getPosition().getY());
        Dimensions dimensions2 = new Dimensions(4, 4);

        Assertions.assertFalse(arena.hasAdjacentBlock(position2, dimensions2));

        Position position3 = new Position(
                block.getPosition().getX() + block.getDimensions().getWidth() + 1,
                block.getPosition().getY());
        Dimensions dimensions3 = new Dimensions(4, 4);

        Assertions.assertFalse(arena.hasAdjacentBlock(position3, dimensions3));

        Position position4 = new Position(
                block.getPosition().getX() - 4,
                block.getPosition().getY());
        Dimensions dimensions4 = new Dimensions(4, 4);

        Assertions.assertTrue(arena.hasAdjacentBlock(position4, dimensions4));

        Position position5 = new Position(
                block.getPosition().getX() - 4 - 1,
                block.getPosition().getY());
        Dimensions dimensions5 = new Dimensions(4, 4);

        Assertions.assertFalse(arena.hasAdjacentBlock(position5, dimensions5));

        Position position6 = new Position(
                block.getPosition().getX() - 4 + 1,
                block.getPosition().getY());
        Dimensions dimensions6 = new Dimensions(4, 4);

        Assertions.assertFalse(arena.hasAdjacentBlock(position6, dimensions6));
    }

    @Test
    public void blockCollision() throws FileNotFoundException, URISyntaxException {
        List<Block> blocks = arena.getBlocks();

        while(!blocks.isEmpty()) blocks.remove(0);

        Block block = new Block(new Position(32, 32), "DirtBlock");

        blocks.add(0, block);

        Position position1 = new Position(
                block.getPosition().getX() + block.getDimensions().getWidth(),
                block.getPosition().getY());
        Dimensions dimensions1 = new Dimensions(4, 4);

        Assertions.assertFalse(arena.collidesWithBlocks(position1, dimensions1));

        Position position2 = new Position(
                block.getPosition().getX() + block.getDimensions().getWidth() - 1,
                block.getPosition().getY());
        Dimensions dimensions2 = new Dimensions(4, 4);

        Assertions.assertTrue(arena.collidesWithBlocks(position2, dimensions2));

        Position position3 = new Position(
                block.getPosition().getX() + block.getDimensions().getWidth() + 1,
                block.getPosition().getY());
        Dimensions dimensions3 = new Dimensions(4, 4);

        Assertions.assertFalse(arena.collidesWithBlocks(position3, dimensions3));

        Position position4 = new Position(
                block.getPosition().getX() - 4,
                block.getPosition().getY());
        Dimensions dimensions4 = new Dimensions(4, 4);

        Assertions.assertFalse(arena.collidesWithBlocks(position4, dimensions4));

        Position position5 = new Position(
                block.getPosition().getX() - 4 - 1,
                block.getPosition().getY());
        Dimensions dimensions5 = new Dimensions(4, 4);

        Assertions.assertFalse(arena.collidesWithBlocks(position5, dimensions5));

        Position position6 = new Position(
                block.getPosition().getX() - 4 + 1,
                block.getPosition().getY());
        Dimensions dimensions6 = new Dimensions(4, 4);

        Assertions.assertTrue(arena.collidesWithBlocks(position6, dimensions6));

        Position position7 = new Position(
                block.getPosition().getX() - 1,
                block.getPosition().getY() - 2);
        Dimensions dimensions7 = new Dimensions(8, 4);

        Assertions.assertTrue(arena.collidesWithBlocks(position7, dimensions7));

        Position position8 = new Position(
                block.getPosition().getX() - 1,
                block.getPosition().getY() + 1);
        Dimensions dimensions8 = new Dimensions(2, 4);

        Assertions.assertTrue(arena.collidesWithBlocks(position8, dimensions8));

    }

}
