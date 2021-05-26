package Terrarius.Model.Game.items;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Utils.Dimensions;

public class BlockPlacer extends Item {
    public BlockPlacer(Hero hero) {
        super(hero, new Dimensions(0, 0));
    }

    @Override
    public void updateStats() {

    }
}
