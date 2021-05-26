package Terrarius.Model.Game.arena;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.blocks.Block;
import Terrarius.Model.Game.elements.blocks.WoodBlock;
import Terrarius.Model.Game.elements.enemies.Enemy;
import Terrarius.Model.Game.elements.enemies.Zombie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public class LoaderArenaBuilderTest {
    LoaderArenaBuilder loaderArenaBuilder;

    @BeforeEach
    public void setup() throws FileNotFoundException, URISyntaxException {
        loaderArenaBuilder = new LoaderArenaBuilder(1);
    }

    @Test
    public void dimensions() {
        Assertions.assertEquals(128, loaderArenaBuilder.getWidth());
        Assertions.assertEquals(64, loaderArenaBuilder.getHeight());
    }

    @Test
    public void createBlocks() {
        List<Block> blocks = loaderArenaBuilder.createBlocks();
        Assertions.assertEquals(173, blocks.size());

        Block firstBlock = blocks.get(0);
        Assertions.assertTrue(firstBlock instanceof WoodBlock);
    }

    @Test
    public void createEnemies() {
        List<Enemy> enemies = loaderArenaBuilder.createEnemies();
        Assertions.assertEquals(4, enemies.size());

        Enemy firstEnemy = enemies.get(0);
        Assertions.assertEquals(0, firstEnemy.getPosition().getX());
        Assertions.assertEquals(0, firstEnemy.getPosition().getY());
        Assertions.assertEquals(1, firstEnemy.getStats().getLevel().getNumLevel());
        Assertions.assertEquals(10, firstEnemy.getStats().getLevel().getCurrentXP());
        Assertions.assertTrue(firstEnemy instanceof Zombie);
    }

    @Test
    public void createHero() {
        Hero hero = loaderArenaBuilder.createHero();
        Assertions.assertEquals(36, hero.getPosition().getX());
        Assertions.assertEquals(24, hero.getPosition().getY());
    }
}
