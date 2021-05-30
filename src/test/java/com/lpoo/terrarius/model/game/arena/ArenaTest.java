package com.lpoo.terrarius.model.game.arena;

import com.lpoo.terrarius.model.game.Level;
import com.lpoo.terrarius.model.game.elements.enemies.Enemy;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.model.game.items.tools.Tool;
import com.lpoo.terrarius.utils.Dimensions;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.Block;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import static com.lpoo.terrarius.utils.GameConstants.SCREEN_WIDTH;


public class ArenaTest {
    Arena arena;
    Hero hero;

    @BeforeEach
    public void setUp() throws FileNotFoundException, URISyntaxException {

        arena = new Arena();
        hero = new Hero(new Position(0, 0));

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

        blocks.clear();

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

    @Test
    public void blockBreaking() throws FileNotFoundException, URISyntaxException {
        List<Block> blocks = arena.getBlocks();

        blocks.clear();

        Block block = new Block(new Position(32, 32), "StoneBlock");

        int original_hp = block.getHP();

        blocks.add(0, block);

        arena.breakBlock(new Position(32, 32), new Tool(hero, "Shovel"));

        Assertions.assertEquals(original_hp, blocks.get(0).getHP());

        arena.breakBlock(new Position(32, 32), new Tool(hero, "Axe"));

        Assertions.assertEquals(original_hp, blocks.get(0).getHP());

        arena.breakBlock(new Position(32, 32), new Tool(hero, "Sword"));

        Assertions.assertEquals(original_hp, blocks.get(0).getHP());

        arena.breakBlock(new Position(32, 32), new Tool(hero, "Pickaxe"));

        Assertions.assertNotEquals(original_hp, blocks.get(0).getHP());

        original_hp = blocks.get(0).getHP();

        arena.breakBlock(new Position(31, 32), new Tool(hero, "Pickaxe"));

        Assertions.assertEquals(original_hp, blocks.get(0).getHP());

    }

    @Test
    public void blockPlacing() throws FileNotFoundException, URISyntaxException {
        List<Block> blocks = arena.getBlocks();

        this.arena.getHero().getToolBar().getBlockPouch().incrementBlock(
                new Block(new Position(0, 0), "DirtBlock"));

        blocks.clear();

        Block block = new Block(new Position(32, 32), "StoneBlock");

        blocks.add(0, block);

        arena.placeBlock(arena.getEnemies().get(0).getPosition());
        arena.placeBlock(arena.getHero().getPosition());
        arena.placeBlock(new Position(32, 32));

        Assertions.assertEquals(1, blocks.size());

        this.arena.getEnemies().clear();

        arena.placeBlock(new Position(50, 23));

        Assertions.assertEquals(2, blocks.size());
    }

    @Test
    public void attack() throws FileNotFoundException, URISyntaxException {
        List<Enemy> enemies = arena.getEnemies();

        enemies.clear();

        enemies.add(0, new Enemy(new Position(32, 32), new Level(10, 1), "Zombie"));

        int original_hp = enemies.get(0).getStats().getHp();

        Tool sword =  new Tool(hero, "Sword");

        this.arena.heroAttack(new Position(31, 32), sword);

        Assertions.assertEquals(original_hp, enemies.get(0).getStats().getHp());

        this.arena.heroAttack(new Position(32 + enemies.get(0).getDimensions().getWidth(), 32), sword);

        Assertions.assertEquals(original_hp, enemies.get(0).getStats().getHp());

        this.arena.heroAttack(new Position(32, 32), sword);

        Assertions.assertNotEquals(original_hp, enemies.get(0).getStats().getHp());

    }
}
