package Model.arena;

import Model.Dimensions;
import Model.Position;
import Model.elements.Hero;
import Model.elements.blocks.Block;
import Model.elements.enemies.Enemy;
import Model.map.MapChooser;
import Model.map.MapZone;

import java.util.ArrayList;
import java.util.List;


public class Arena {
    private final Dimensions dimensions;

    private Hero hero;

    private List<MapZone> mapZoneList = new ArrayList<>();
    private Integer currentMapIndex;

    private List<Enemy> enemies;
    private List<Block> blocks;

    private MapChooser mapChooser;

    public Arena(int width, int height) {
        this.dimensions = new Dimensions(height, width);
        this.mapChooser = new MapChooser();
        this.currentMapIndex = 0;
    }

    public void update(){
        if (hero.getPosition().getX() + hero.getDimensions().getWidth() > getWidth()){
            currentMapIndex++;

            if (currentMapIndex >= mapZoneList.size()){
                mapZoneList.add(mapChooser.getMap(hero.getLevel().getNumLevel()));
            }

            this.enemies = mapZoneList.get(currentMapIndex).getEnemies();
            this.blocks = mapZoneList.get(currentMapIndex).getBlocks();
            this.hero.setPosition(mapZoneList.get(currentMapIndex).getLeftSpawn());
        }
        else if(hero.getPosition().getX() < 0){

            if(currentMapIndex == 0){
                mapZoneList.add(0, mapChooser.getMap(hero.getLevel().getNumLevel()));
            }
            else{
                currentMapIndex--;
            }

            this.enemies = mapZoneList.get(currentMapIndex).getEnemies();
            this.blocks = mapZoneList.get(currentMapIndex).getBlocks();
            this.hero.setPosition(mapZoneList.get(currentMapIndex).getRightSpawn());
        }
    }

    public void addMap(int index, MapZone mapZone){
        this.mapZoneList.add(index, mapZone);
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

}
