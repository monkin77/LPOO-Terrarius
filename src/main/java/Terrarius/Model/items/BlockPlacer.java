package Terrarius.Model.items;

import Terrarius.Model.Dimensions;
import Terrarius.Model.elements.Hero;

public class BlockPlacer extends Item{
    public BlockPlacer(Hero hero) {
        super(hero, new Dimensions(0, 0));
    }

    @Override
    public void updateStats() {

    }
}
