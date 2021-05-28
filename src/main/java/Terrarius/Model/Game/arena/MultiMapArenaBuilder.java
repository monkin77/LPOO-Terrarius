package Terrarius.Model.Game.arena;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.map.MapChooser;
import Terrarius.Model.Game.map.MapZone;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

// TODO: Repeated in LoaderArenaBuilder
public class MultiMapArenaBuilder{

    public Arena createArena() {

        MapChooser mapChooser = new MapChooser();

        MapZone mapZone = mapChooser.getMap(1);

        Arena arena = new Arena(mapZone.getDimensions().getWidth(), mapZone.getDimensions().getHeight());

        Hero hero = null;
        try {
            hero = new Hero(mapZone.getLeftSpawn());
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }

        arena.setHero(hero);
        arena.setEnemies(mapZone.getEnemies());
        arena.setBlocks(mapZone.getBlocks());
        arena.addMap(0, mapZone);
        return arena;
    }
}
