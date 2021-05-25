package Terrarius.Model.arena;

import Terrarius.Model.Dimensions;
import Terrarius.Model.Position;
import Terrarius.Model.elements.Element;
import Terrarius.Model.elements.Hero;
import Terrarius.Model.elements.blocks.*;
import Terrarius.Model.elements.enemies.Enemy;
import Terrarius.Model.items.Item;
import Terrarius.Model.items.tools.Tool;
import com.sun.source.tree.ReturnTree;

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

    private boolean isElement1InElement2(Element element1, Element element2) {
        boolean left_elem_in_block =
                element1.getPosition().getX() >= element2.getPosition().getX() &&
                        element1.getPosition().getX() <= element2.getPosition().getX() + element2.getDimensions().getWidth() - 1;

        boolean right_elem_in_block =
                element1.getPosition().getX() + element1.getDimensions().getWidth() - 1 >= element2.getPosition().getX() &&
                        element1.getPosition().getX() + element1.getDimensions().getWidth() - 1 <= element2.getPosition().getX() + element2.getDimensions().getWidth() - 1;

        if (left_elem_in_block || right_elem_in_block){

            boolean top_elem_in_block =
                    element1.getPosition().getY() >= element2.getPosition().getY() &&
                            element1.getPosition().getY() <= element2.getPosition().getY() + element2.getDimensions().getHeight() - 1;

            boolean bottom_elem_in_block =
                    element1.getPosition().getY() + element1.getDimensions().getHeight() - 1 >= element2.getPosition().getY() &&
                            element1.getPosition().getY() + element1.getDimensions().getHeight() - 1 <= element2.getPosition().getY() + element2.getDimensions().getHeight() - 1;

            if (top_elem_in_block || bottom_elem_in_block) return true;
        }
        return false;
    }

    /**
     * For situations where a block is floating and collides with the middle of the element
     */
    private boolean isElement2InElement1(Element element1, Element element2) {
        boolean left_block_in_elem =
                element2.getPosition().getX() >= element1.getPosition().getX() &&
                        element2.getPosition().getX() <= element1.getPosition().getX() + element1.getDimensions().getWidth() - 1;

        boolean right_block_in_elem =
                element2.getPosition().getX() +element2.getDimensions().getWidth() - 1 >= element1.getPosition().getX() &&
                        element2.getPosition().getX() + element2.getDimensions().getWidth() - 1 <= element1.getPosition().getX() + element1.getDimensions().getWidth() - 1;

        if (left_block_in_elem || right_block_in_elem){

            boolean top_block_in_elem =
                    element2.getPosition().getY() >= element1.getPosition().getY() &&
                            element2.getPosition().getY() <= element1.getPosition().getY() + element1.getDimensions().getHeight() - 1;

            boolean bottom_block_in_elem =
                    element2.getPosition().getY() + element2.getDimensions().getHeight() - 1 >= element1.getPosition().getY() &&
                            element2.getPosition().getY() + element2.getDimensions().getHeight() - 1 <= element1.getPosition().getY() + element1.getDimensions().getHeight() - 1;

            if (top_block_in_elem || bottom_block_in_elem) return true;
        }
        return false;
    }

    public boolean collides(Element element, Position position, Dimensions dimensions){
        return collides(element, new Element(position, dimensions));
    }

    public boolean collides(Element element1, Element element2){
        return (isElement1InElement2(element1, element2) || isElement2InElement1(element1, element2));
    }

    public boolean collidesWithBlocks(Position position, Dimensions dimensions){
        return this.collidesWithBlocks(new Element(position, dimensions));
    }

    public boolean collidesWithBlocks(Element element){
        for (Block block : this.blocks) {
            if (collides(element, block)) return true;
        }
        return false;
    }

    public boolean collides(Position position, Hero hero) {
        if (this.collidesWithBlocks(hero)) return true;

        if(hero.getToolBar().getActiveItem() != null) {
            Item activeItem = hero.getToolBar().getActiveItem();
            Position copyPos = activeItem.getPosition(position);
            return collidesWithBlocks(new Element(copyPos, activeItem.getDimensions()));
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

        if (tool.getStats().getMiningHardness() >= block.getHardness()){
            block.setHp(block.getHP() - tool.getStats().getMiningPower());
            if (block.getHP() <= 0){
                hero.getToolBar().getBlockPouch().incrementBlock(block);
                blocks.remove(block);
            }
        }
    }

    public void placeBlock(Position position){

        String blockName = hero.getToolBar().getBlockPouch().getCurrentBlockName();

        Position gridPosition = new Position(position.getX()/4*4, position.getY()/4*4);

        if (this.collidesWithBlocks(new Element(gridPosition, new Dimensions(4, 4)))
                || hero.getToolBar().getBlockPouch().getCurrentBlockQuantity() <= 0)
            return;

        Block block;

        switch (blockName) {
            case "DirtBlock":  block = new DirtBlock(gridPosition);  break;
            case "StoneBlock": block = new StoneBlock(gridPosition); break;
            case "WoodBlock":  block = new WoodBlock(gridPosition);  break;
            default: return;
        }

        for (Enemy enemy : this.enemies){
            if (collides(enemy, block)) return;
        }
        if (collides(hero, block)) return;

        this.blocks.add(block);
        hero.getToolBar().getBlockPouch().decrementBlock(block);

    }
}
