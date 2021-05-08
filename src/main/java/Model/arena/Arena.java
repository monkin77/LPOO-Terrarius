package Model.arena;

import Model.Dimensions;
import Model.Position;
import Model.elements.Hero;
import Model.elements.blocks.Block;
import Model.elements.enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

// TODO: Refactor
public class Arena {
    private final Dimensions dimensions;

    private Hero hero;

    private List<Enemy> enemies;
    private List<Block> blocks;

    public Arena(int width, int height) {
        this.dimensions = new Dimensions(height, width);
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
        return this.dimensions.getWidth();
    }

    public int getHeight() {
        return this.dimensions.getHeight();
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public boolean isEmpty(Position position){
        for(Block block : this.blocks){
            if(block.getPosition().equals(position))
                return false;
        }
        return true;
    }

    // CHANGE NAME, ADJACENT IS NOT ENOUGH. DOES NOT WORK IF IT'S LARGER/TALLER THAN THE BLOCKS
    // SHOULD IT BE LIKE THIS?
    public boolean hasAdjacent(Position position, Dimensions dimensions){
        for (Block block : this.blocks){

            boolean top_side_touches =
                    position.getY() >= block.getPosition().getY() &&
                            position.getY() <= block.getPosition().getY() + block.getDimensions().getHeight() - 1;

            boolean bottom_side_touches =
                    position.getY() + dimensions.getHeight() - 1 >= block.getPosition().getY() &&
                            position.getY() + dimensions.getHeight() - 1 <= block.getPosition().getY() + block.getDimensions().getHeight() - 1;

            boolean right_side_touches = position.getX() + dimensions.getWidth() == block.getPosition().getX();

            boolean left_side_touches = position.getX() == block.getPosition().getX() + block.getDimensions().getWidth();

            if ((right_side_touches || left_side_touches) && (top_side_touches || bottom_side_touches)) return true;

        }

        return false;
    }

    public boolean collides(Position position, Dimensions dimensions){

        for (Block block : this.blocks){ //Some conditions could have been omitted, however they might become needed

            //read has: left side of element (position and dimensions) inside of block
            boolean left_elem_in_block =
                    position.getX() >= block.getPosition().getX() &&
                    position.getX() <= block.getPosition().getX() + block.getDimensions().getWidth() - 1;

            boolean right_elem_in_block =
                    position.getX() + dimensions.getWidth() - 1 >= block.getPosition().getX() &&
                    position.getX() + dimensions.getWidth() - 1 <= block.getPosition().getX() + block.getDimensions().getWidth() - 1;

            if (left_elem_in_block || right_elem_in_block){

                boolean top_elem_in_block =
                        position.getY() >= block.getPosition().getY() &&
                                position.getY() <= block.getPosition().getY() + block.getDimensions().getHeight() - 1;

                boolean bottom_elem_in_block =
                        position.getY() + dimensions.getHeight() - 1 >= block.getPosition().getY() &&
                                position.getY() + dimensions.getHeight() - 1 <= block.getPosition().getY() + block.getDimensions().getHeight() - 1;

                if (top_elem_in_block || bottom_elem_in_block) return true;
            }

            boolean left_block_in_elem =
                    block.getPosition().getX() >= position.getX() &&
                            block.getPosition().getX() <= position.getX() + dimensions.getWidth() - 1;

            boolean right_block_in_elem =
                    block.getPosition().getX() + block.getDimensions().getWidth() - 1 >= position.getX() &&
                            block.getPosition().getX() + block.getDimensions().getWidth() - 1 <= position.getX() + dimensions.getWidth() - 1;

            if (left_block_in_elem || right_block_in_elem){

                boolean top_block_in_elem =
                        block.getPosition().getY() >= position.getY() &&
                        block.getPosition().getY() <= position.getY() + dimensions.getHeight() - 1;

                boolean bottom_block_in_elem =
                        block.getPosition().getY() + block.getDimensions().getHeight() - 1 >= position.getY() &&
                        block.getPosition().getY() + block.getDimensions().getHeight() - 1 <= position.getY() + dimensions.getHeight() - 1;

                if (top_block_in_elem || bottom_block_in_elem) return true;
            }
        }

        return false;
    }
}
