package Model.arena;

import Model.Level;
import Model.Position;
import Model.elements.Hero;
import Model.elements.blocks.Block;
import Model.elements.blocks.StoneBlock;
import Model.elements.enemies.Enemy;
import Model.elements.enemies.Zombie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultArenaBuilderTest {
    DefaultArenaBuilder defaultArenaBuilder;

    @BeforeEach
    void setUp() {
        this.defaultArenaBuilder = new DefaultArenaBuilder(20, 20);
    }

    @Test
    public void getters() {
        Assertions.assertEquals(20, this.defaultArenaBuilder.getHeight());
        Assertions.assertEquals(20, this.defaultArenaBuilder.getWidth());
    }

    @Test
    public void creation() {
        Arena arena = this.defaultArenaBuilder.createArena();

        Hero hero = new Hero(new Position(this.defaultArenaBuilder.getWidth()/2, this.defaultArenaBuilder.getHeight()/2));

        Assertions.assertEquals(hero.getPosition(), arena.getHero().getPosition());

        List<Enemy> enemies = new ArrayList<>();

        enemies.add(new Zombie(new Position(0, 0), new Level(1, 0)));
        enemies.add(new Zombie(new Position(this.defaultArenaBuilder.getWidth() /2, this.defaultArenaBuilder.getHeight()/2), new Level(3, 0)));
        enemies.add(new Zombie(new Position(this.defaultArenaBuilder.getWidth() - 1, this.defaultArenaBuilder.getHeight() - 1), new Level(5, 10)));

        Assertions.assertEquals(enemies.size(), arena.getEnemies().size());

        List<Block> blocks = new ArrayList<>();

        for(int x = 0; x < this.defaultArenaBuilder.getWidth(); x++){
            blocks.add( new StoneBlock( new Position(x, 0) ) );
            blocks.add( new StoneBlock( new Position(x, this.defaultArenaBuilder.getHeight() - 1) ) );
        }

        for(int y = 1; y < this.defaultArenaBuilder.getHeight() - 1; y++){
            blocks.add( new StoneBlock( new Position(0, y) ) );
            blocks.add( new StoneBlock( new Position(this.defaultArenaBuilder.getWidth() - 1, y) ) );
        }

        Assertions.assertEquals(blocks.size(), arena.getBlocks().size());
    }
}