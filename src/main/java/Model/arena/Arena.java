package Model.arena;

import Model.Dimensions;
import Model.Position;
import Model.elements.Element;
import Model.elements.Hero;
import Model.elements.blocks.Block;
import Model.elements.blocks.DirtBlock;
import Model.elements.blocks.StoneBlock;
import Model.elements.blocks.WoodBlock;
import Model.elements.enemies.Enemy;
import Model.items.tools.Tool;
import Model.items.Item;

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

    public boolean collides(Position position, Dimensions dimensions){
        for (Block block : this.blocks) {
            if (isElementInBlock(position, dimensions, block) || isBlockInElement(position, dimensions, block))
                return true;
        }

        return false;
    }

    public boolean collides(Position position, Hero hero) {
        if (this.collides(position, hero.getDimensions()))
            return true;

        if(hero.getToolBar().getActiveItem() != null) {
            Item activeItem = hero.getToolBar().getActiveItem();
            Position copyPos = activeItem.getPosition(position);

            for (Block block : this.blocks) {
                if (isElementInBlock(copyPos, activeItem.getDimensions(), block) || isBlockInElement(copyPos, activeItem.getDimensions(), block))
                    return true;
            }
        }

        return false;
    }

    private boolean isElementInBlock(Position position, Dimensions dimensions, Block block) {
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
        return false;
    }

    /**
     * For situations where a block is floating and collides with the middle of the element
     */
    private boolean isBlockInElement(Position position, Dimensions dimensions, Block block) {
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
        return false;
    }

    public void breakBlock(Position position, Tool tool){
        Block block = null;

        Position realPosition = new Position(position.getX()/4 * 4, position.getY()/4 * 4); //Make this better somehow

        for (Block block1 : blocks){
            if (block1.getPosition().equals(realPosition)){
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

        position = new Position(position.getX()/4*4, position.getY()/4*4);

        if (this.collides(position, new Dimensions(4, 4))
                || hero.getToolBar().getBlockPouch().getCurrentBlockQuantity() <= 0)
            return;

        Block block;

        if (blockName.equals("DirtBlock")){
            block = new DirtBlock(position);
        }
        else if (blockName.equals("StoneBlock")){
            block = new StoneBlock(position);
        }
        else if (blockName.equals("WoodBlock")){
            block = new WoodBlock(position);
        }
        else{
            return;
        }

        for (Enemy enemy : this.enemies){
            if (isBlockInElement(enemy.getPosition(), enemy.getDimensions(), block) ||
                    isElementInBlock(enemy.getPosition(), enemy.getDimensions(), block))
                return;
        }
        if ((isBlockInElement(this.hero.getPosition(), this.hero.getDimensions(), block) ||
                isElementInBlock(this.hero.getPosition(), this.hero.getDimensions(), block)))
            return;

        this.blocks.add(block);
        hero.getToolBar().getBlockPouch().decrementBlock(block);

    }
}
