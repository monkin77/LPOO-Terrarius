package com.lpoo.terrarius.model.game.arena;

import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.Block;
import com.lpoo.terrarius.model.game.elements.enemies.Enemy;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.model.game.items.tools.Tool;
import com.lpoo.terrarius.utils.Dimensions;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.lpoo.terrarius.utils.GameConstants.DEFAULT_BLOCK_DIMENSIONS;


public class Arena {
    private Hero hero;

    private final List<MapZone> mapZoneList;
    private final MapChooser mapChooser;
    private Integer currentMapIndex;

    public enum BOUNDARY {MAP_LEFT, MAP_RIGHT, MAP_SAME}

    public Arena() {
        this.mapChooser = new MapChooser();
        this.mapZoneList = new ArrayList<>();

        mapZoneList.add(mapChooser.getMap(1));
        this.currentMapIndex = 0;

        try {
            this.hero = new Hero(mapZoneList.get(0).getLeftSpawn());
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public BOUNDARY checkMapZoneAndUpdate() {
        if (hero.getPosition().getX() + hero.getDimensions().getWidth() > getWidth()) {
            currentMapIndex++;

            if (currentMapIndex >= mapZoneList.size())
                mapZoneList.add(mapChooser.getMap(hero.getStats().getLevel().getNumLevel()));

            return BOUNDARY.MAP_RIGHT;
        }
        else if (hero.getPosition().getX() < 0) {

            if(currentMapIndex == 0)
                mapZoneList.add(0, mapChooser.getMap(hero.getStats().getLevel().getNumLevel()));
            else
                currentMapIndex--;
            return BOUNDARY.MAP_LEFT;
        }
        return BOUNDARY.MAP_SAME;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Enemy> getEnemies() {
        return mapZoneList.get(currentMapIndex).getEnemies();
    }

    public List<Block> getBlocks() {
        return mapZoneList.get(currentMapIndex).getBlocks();
    }

    public int getWidth() {
        return mapZoneList.get(currentMapIndex).getDimensions().getWidth();
    }

    public int getHeight() {
        return mapZoneList.get(currentMapIndex).getDimensions().getHeight();
    }

    public Dimensions getDimensions() {
        return mapZoneList.get(currentMapIndex).getDimensions();
    }

    public List<MapZone> getMapZoneList() {
        return mapZoneList;
    }

    public Integer getCurrentMapIndex() {
        return currentMapIndex;
    }

    public boolean hasAdjacentBlock(Position position, Dimensions dimensions) {
        for (Block block : this.getBlocks()) {

            boolean top_side_touches =
                    position.getY() >= block.getPosition().getY() &&
                            position.getY() <= block.getPosition().getY() + block.getDimensions().getHeight() - 1;

            boolean bottom_side_touches =
                    position.getY() + dimensions.getHeight() - 1 >= block.getPosition().getY() &&
                            position.getY() + dimensions.getHeight() - 1 <= block.getPosition().getY() + block.getDimensions().getHeight() - 1;

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
        for (Block block : this.getBlocks()) {
            if (Position.checkElementsCollision(position, dimensions, block.getPosition(), block.getDimensions()))
                return true;
        }
        return false;
    }

    public void breakBlock(Position position, Tool tool){
        Position gridPosition = new Position(position.getX()/4 * 4, position.getY()/4 * 4);

        Block block = mapZoneList.get(currentMapIndex).getBlockInPosition(gridPosition);
        if (block == null) return;

        if (tool.getStats().getMiningHardness() >= block.getHardness()) {
            block.setHp(block.getHP() - tool.getStats().getMiningPower());

            if (block.getHP() <= 0){
                hero.getToolBar().getBlockPouch().incrementBlock(block);
                getBlocks().remove(block);
            }
        }
    }

    public void placeBlock(Position position){
        Position gridPosition = new Position(position.getX()/4*4, position.getY()/4*4);

        if (this.collidesWithBlocks(gridPosition, new Dimensions(DEFAULT_BLOCK_DIMENSIONS))
                || hero.getToolBar().getBlockPouch().getCurrentBlockQuantity() <= 0)
            return;

        Block block = this.hero.getToolBar().getBlockPouch().generateCurrentBlock(gridPosition);

        for (Enemy enemy : this.getEnemies()){
            if (Position.checkElementsCollision(enemy.getPosition(), enemy.getDimensions(),
                    block.getPosition(), block.getDimensions()))
                return;
        }

        if (Position.checkElementsCollision(hero.getPosition(), hero.getDimensions(),
                block.getPosition(), block.getDimensions()))
            return;

        this.getBlocks().add(block);
        hero.getToolBar().getBlockPouch().decrementBlock(block);
    }

    public void heroAttack(Position targetPosition, Tool tool){
        for (Enemy enemy : this.getEnemies()){
            if (Position.checkElementsCollision(enemy.getPosition(), enemy.getDimensions(), targetPosition, new Dimensions(1, 1))){
                enemy.setHP(enemy.getStats().getHp() - hero.getStats().getPower() - tool.getStats().getFightingPower());
                if (enemy.getStats().getHp() <= 0){
                    hero.getStats().getLevel().increaseXP(enemy.getStats().getLevel().calcXpDrop());
                }
            }
        }
        this.getEnemies().removeIf(n -> (n.getStats().getHp() <= 0));
    }
}
