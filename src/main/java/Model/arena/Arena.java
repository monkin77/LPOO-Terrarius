package Model.arena;

import Model.Dimensions;
import Model.Position;
import Model.elements.Element;
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

    public boolean collides(Position position, Dimensions dimensions){

        for (Block block : this.blocks){

            boolean left_collision =
                    position.getX() >= block.getPosition().getX() &&
                    position.getX() <= block.getPosition().getX() + block.getDimensions().getWidth() - 1;

            boolean right_collision =
                    position.getX() + dimensions.getWidth() - 1 >= block.getPosition().getX() &&
                    position.getX() + dimensions.getWidth() - 1 <= block.getPosition().getX() + block.getDimensions().getWidth() - 1;

            if (!left_collision && !right_collision) continue;

            boolean top_collision =
                    position.getY() >= block.getPosition().getY() &&
                    position.getY() <= block.getPosition().getY() + block.getDimensions().getHeight() - 1;

            boolean bottom_collision =
                    position.getY() + dimensions.getHeight() - 1 >= block.getPosition().getY() &&
                    position.getY() + dimensions.getHeight() - 1 <= block.getPosition().getY() + block.getDimensions().getHeight() - 1;

            if (top_collision || bottom_collision) return true;

        }

        return false;
    }
}
