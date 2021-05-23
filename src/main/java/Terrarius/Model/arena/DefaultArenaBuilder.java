package Terrarius.Model.arena;

import Terrarius.Model.Level;
import Terrarius.Model.Position;
import Terrarius.Model.elements.Hero;
import Terrarius.Model.elements.blocks.Block;
import Terrarius.Model.elements.blocks.StoneBlock;
import Terrarius.Model.elements.blocks.WoodBlock;
import Terrarius.Model.elements.enemies.Enemy;
import Terrarius.Model.elements.enemies.Zombie;

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

        for(int x = 0; x < this.width/4; x++){
            blocks.add( new StoneBlock( new Position(x * 4, 0) ) );
            blocks.add( new StoneBlock( new Position(x * 4, this.height - 4) ) );
        }

        for(int y = 1; y < height/4; y++){
            blocks.add( new StoneBlock( new Position(0, y * 4) ) );
            blocks.add( new StoneBlock( new Position(this.width - 4, y * 4) ) );
        }

        for (int y = height/4 - 2; y >= height/4 - 4; y--){
            blocks.add(new WoodBlock( new Position(16, y*4)));
        }

        return blocks;
    }

    @Override
    protected List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        enemies.add(new Zombie(new Position(this.width/4, this.height/4), new Level(1, 10)));
        enemies.add(new Zombie(new Position(this.width/4 + 8, this.height/4 + 4), new Level(1, 10)));
        enemies.add(new Zombie(new Position(4, 4), new Level(1, 0)));
        enemies.add(new Zombie(new Position(this.width - 8, this.height - 12), new Level(5, 10)));

        return enemies;
    }

    @Override
    protected Hero createHero() {
        return new Hero(new Position(this.width/2, this.height/2));
    }
}
