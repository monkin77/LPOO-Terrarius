package Terrarius.Model.Game.arena;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.map.MapChooser;
import Terrarius.Model.Game.map.MapZone;

// TODO: Repeated in LoaderArenaBuilder
public class MultiMapArenaBuilder{

    public Arena createArena() {

        MapChooser mapChooser = new MapChooser();

        MapZone mapZone = mapChooser.getMap(1);

        Arena arena = new Arena(mapZone.getDimensions().getWidth(), mapZone.getDimensions().getHeight());

        Hero hero = new Hero(mapZone.getLeftSpawn());

        arena.setHero(hero);
        arena.setEnemies(mapZone.getEnemies());
        arena.setBlocks(mapZone.getBlocks());
        arena.addMap(0, mapZone);
        return arena;
    }
}
