package Terrarius.Model.Game.arena;

import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.buffs.*;
import Terrarius.Model.Game.items.tools.Axe;
import Terrarius.Model.Game.items.tools.Pickaxe;
import Terrarius.Model.Game.items.tools.Shovel;
import Terrarius.Model.Game.items.tools.Sword;
import Terrarius.Model.Game.map.MapChooser;
import Terrarius.Model.Game.map.MapZone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiMapArenaBuilder{

    public Arena createArena() {

        MapChooser mapChooser = new MapChooser();

        MapZone mapZone = mapChooser.getMap(1);

        Arena arena = new Arena(mapZone.getDimensions().getWidth(), mapZone.getDimensions().getHeight());

        Hero hero = new Hero(mapZone.getLeftSpawn());
        hero.addItem(1, new Axe(hero));  // TODO: CHANGE WHEN WE HAVE ITEM CRAFTING
        hero.addItem(2, new Apple(hero));
        hero.addItem(3, new Banana(hero));
        hero.addItem(4, new BattlePotion(hero));
        hero.addItem(5, new ElasticPotion(hero));
        hero.addItem(6, new Pickaxe(hero));
        hero.addItem(7, new Shovel(hero));
        hero.addItem(8, new Sword(hero));
        hero.addItem(9, new SwiftnessPotion(hero));

        arena.setHero(hero);
        arena.setEnemies(mapZone.getEnemies());
        arena.setBlocks(mapZone.getBlocks());
        arena.addMap(0, mapZone);
        return arena;
    }
}
