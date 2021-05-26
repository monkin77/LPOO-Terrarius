package Terrarius.Model.Game.arena;

import Terrarius.Model.Game.items.tools.Tool;
import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.blocks.Block;
import Terrarius.Model.Game.elements.enemies.Enemy;

import java.util.ArrayList;
import java.util.List;


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

    public boolean hasAdjacentBlock(Position position, Dimensions dimensions) {
        for (Block block : this.blocks){

            boolean top_side_touches =
                    position.getY() >= block.getPosition().getY() &&
                            position.getY() <= block.getPosition().getY() + block.getDimensions().getHeight() - 1;

            boolean bottom_side_touches =
                    position.getY() + dimensions.getHeight() - 1 >= block.getPosition().getY() &&
                            position.getY() + dimensions.getHeight() - 1 <= block.getPosition().getY() + block.getDimensions().getHeight() - 1;

            //Just the Y is "inside" not the X
            boolean element_inside_block =
                    position.getY() >= block.getPosition().getY() &&
                            position.getY() + dimensions.getHeight() <= block.getPosition().getY() + block.getDimensions().getHeight() - 1;

            boolean block_inside_element =
                    block.getPosition().getY() >= position.getY() &&
                            block.getPosition().getY() + block.getDimensions().getHeight() - 1 <= position.getY() + dimensions.getHeight();

            boolean right_side_touches = position.getX() + dimensions.getWidth() == block.getPosition().getX();

            boolean left_side_touches = position.getX() == block.getPosition().getX() + block.getDimensions().getWidth();

            if ((right_side_touches || left_side_touches) &&
                    (top_side_touches || bottom_side_touches || element_inside_block || block_inside_element))
                return true;
        }

        return false;
    }

    public boolean collidesWithBlocks(Position position, Dimensions dimensions){
        if (dimensions.getWidth() <= 0 || dimensions.getHeight() <= 0) return false;
        for (Block block : this.blocks) {
            if (Position.checkElementsCollision(position, dimensions, block.getPosition(), block.getDimensions()))
                return true;
        }
        return false;
    }

    public void breakBlock(Position position, Tool tool){
        Block block = null;

        Position gridPosition = new Position(position.getX()/4 * 4, position.getY()/4 * 4); //Make this better somehow

        for (Block block1 : blocks){
            if (block1.getPosition().equals(gridPosition)){
                block = block1;
                break;
            }
        }

        if (block == null) return;

        if (tool.getStats().getMiningHardness() >= block.getHardness()) {
            block.setHp(block.getHP() - tool.getStats().getMiningPower());

            if (block.getHP() <= 0){
                hero.getToolBar().getBlockPouch().incrementBlock(block);
                blocks.remove(block);
            }
        }
    }

    public void placeBlock(Position position){
        Position gridPosition = new Position(position.getX()/4*4, position.getY()/4*4);

        if (this.collidesWithBlocks(gridPosition, new Dimensions(4, 4)) //TODO make a constant for the default block dimensions
                || hero.getToolBar().getBlockPouch().getCurrentBlockQuantity() <= 0)
            return;

        Block block = this.hero.getToolBar().getBlockPouch().generateCurrentBlock(gridPosition);

        for (Enemy enemy : this.enemies){
            if (Position.checkElementsCollision(enemy.getPosition(), enemy.getDimensions(),
                    block.getPosition(), block.getDimensions()))
                return;
        }

        if (Position.checkElementsCollision(hero.getPosition(), hero.getDimensions(),
                block.getPosition(), block.getDimensions()))
            return;

        this.blocks.add(block);
        hero.getToolBar().getBlockPouch().decrementBlock(block);
    }

    public void heroAttack(Position targetPosition, Tool tool){
        for (Enemy enemy : this.enemies){
            if (Position.checkElementsCollision(enemy.getPosition(), enemy.getDimensions(), targetPosition, new Dimensions(1, 1))){
                enemy.setHP(enemy.getStats().getHp() - hero.getStats().getPower() - tool.getStats().getFightingPower());
                if (enemy.getStats().getHp() <= 0){
                    hero.getStats().getLevel().increaseXP(enemy.getStats().getLevel().calcXpDrop());
                }
            }
        }
        this.enemies.removeIf(n -> (n.getStats().getHp() <= 0));
    }
}
