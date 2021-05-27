package Terrarius.Model.Game.map;

import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.blocks.Block;
import Terrarius.Model.Game.elements.enemies.Enemy;
import Terrarius.Utils.Dimensions;

import java.util.ArrayList;
import java.util.List;

public class MapZone {
    private final Dimensions dimensions;
    private Position leftSpawn;
    private Position rightSpawn;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();

    public MapZone(int width, int height){
        this.dimensions = new Dimensions(height ,width);
    }

    public void setLeftSpawn(Position position){
        this.leftSpawn = position;
    }

    public Position getLeftSpawn(){
        return this.leftSpawn;
    }

    public void setRightSpawn(Position position){
        this.rightSpawn = position;
    }

    public Position getRightSpawn(){
        return this.rightSpawn;
    }

    public Dimensions getDimensions() {
        return dimensions;
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
}
