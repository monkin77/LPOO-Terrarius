package Terrarius.Model.Game.arena;

import Terrarius.Model.Game.elements.Block;
import Terrarius.Model.Game.elements.enemies.Enemy;
import Terrarius.Utils.Dimensions;

import java.util.List;

public abstract class MapBuilder {
    private final Dimensions dimensions;

    public MapBuilder() {
        this.dimensions = createDimensions();
    }

    public MapZone createMap() {
        MapZone mapZone = new MapZone(dimensions.getWidth(), dimensions.getHeight());

        mapZone.setBlocks(this.createBlocks());
        mapZone.setEnemies(this.createEnemies());
        this.createSpawns(mapZone);

        return mapZone;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    protected abstract Dimensions createDimensions();
    protected abstract void createSpawns(MapZone mapZone);
    protected abstract List<Block> createBlocks();
    protected abstract List<Enemy> createEnemies();
}
