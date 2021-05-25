package Terrarius.Model.arena;

import Terrarius.Model.Position;
import Terrarius.Model.elements.Hero;
import Terrarius.Model.elements.blocks.Block;
import Terrarius.Model.elements.enemies.Enemy;
import Terrarius.Model.elements.enemies.Zombie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DefaultArenaBuilderTest {
    DefaultArenaBuilder defaultArenaBuilder;

    @BeforeEach
    public void setup() {
        defaultArenaBuilder = new DefaultArenaBuilder(100, 100);
    }

    @Test
    public void createBlocks() {
        List<Block> blocks = defaultArenaBuilder.createBlocks();
        Assertions.assertEquals(101, blocks.size());
    }

    @Test
    public void createEnemies() {
        List<Enemy> enemies = defaultArenaBuilder.createEnemies();
        Assertions.assertEquals(4, enemies.size());

        Enemy firstEnemy = enemies.get(0);
        Assertions.assertTrue(firstEnemy instanceof Zombie);
        Assertions.assertEquals(new Position(25, 25), firstEnemy.getPosition());
        Assertions.assertEquals(1, firstEnemy.getStats().getLevel().getNumLevel());
    }

    @Test
    public void createHero() {
        Hero hero = defaultArenaBuilder.createHero();
        Assertions.assertEquals(new Position(50, 50), hero.getPosition());
    }
}