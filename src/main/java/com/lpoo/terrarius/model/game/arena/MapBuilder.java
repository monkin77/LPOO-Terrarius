package com.lpoo.terrarius.model.game.arena;

import com.lpoo.terrarius.model.game.elements.Block;
import com.lpoo.terrarius.model.game.elements.enemies.Enemy;
import com.lpoo.terrarius.utils.Dimensions;

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
