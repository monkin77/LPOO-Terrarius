package Model.arena;

import Model.Level;
import Model.Position;
import Model.elements.Hero;
import Model.elements.blocks.Block;
import Model.elements.blocks.StoneBlock;
import Model.elements.enemies.Enemy;
import Model.elements.enemies.Zombie;

import java.util.ArrayList;
import java.util.List;

public class DefaultArenaBuilder extends ArenaBuilder{
    private final int width;
    private final int height;

    public DefaultArenaBuilder(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected int getHeight() {
        return this.height;
    }

    @Override
    protected int getWidth() {
        return this.width;
    }

    @Override
    protected List<Block> createBlocks() {
        List<Block> blocks = new ArrayList<>();

        for(int x = 0; x < this.width; x++){
            blocks.add( new StoneBlock( new Position(x, 0) ) );
            blocks.add( new StoneBlock( new Position(x, this.height - 1) ) );
        }

        for(int y = 1; y < height - 1; y++){
            blocks.add( new StoneBlock( new Position(0, y) ) );
            blocks.add( new StoneBlock( new Position(this.width - 1, y) ) );
        }

        return blocks;
    }

    @Override
    protected List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        enemies.add(new Zombie(new Position(0, 0), new Level(1, 0)));
        enemies.add(new Zombie(new Position(this.width /2, this.height/2), new Level(3, 0)));
        enemies.add(new Zombie(new Position(this.width - 1, this.height - 1), new Level(5, 10)));

        return enemies;
    }

    @Override
    protected Hero createHero() {
        return new Hero(new Position(this.width/2, this.height/2));
    }
}
