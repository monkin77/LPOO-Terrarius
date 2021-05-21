package Model.arena;

import Model.elements.Hero;
import Model.elements.blocks.Block;
import Model.elements.enemies.Enemy;
import Model.map.MapChooser;
import Model.map.MapZone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiMapArenaBuilder{ //TODO temporary

    public Arena createArena() {

        MapChooser mapChooser = new MapChooser();

        MapZone mapZone = mapChooser.getMap(1);

        Arena arena = new Arena(mapZone.getDimensions().getWidth(), mapZone.getDimensions().getHeight());

        arena.setHero(new Hero(mapZone.getLeftSpawn()));
        arena.setEnemies(mapZone.getEnemies());
        arena.setBlocks(mapZone.getBlocks());
        arena.addMap(0, mapZone);
        return arena;
    }
}
