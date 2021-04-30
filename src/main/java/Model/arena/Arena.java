package Model.arena;

import Model.Position;
import Model.elements.Hero;
import Model.elements.blocks.Block;
import Model.elements.enemies.Enemy;

import java.util.List;

public class Arena {
    private final int width;
    private final int height;

    private Hero hero;

    private List<Enemy> enemies;
    private List<Block> blocks;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isEmpty(Position position){
        for(Block block : this.blocks){
            if(block.getPosition().equals(position))
                return false;
        }
        return true;
    }
}
