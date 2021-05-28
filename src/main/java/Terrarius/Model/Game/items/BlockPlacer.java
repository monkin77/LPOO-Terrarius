package Terrarius.Model.Game.items;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Utils.Dimensions;

public class BlockPlacer extends Item {
    public BlockPlacer(Hero hero) {
        super(hero, new Dimensions(3, 3));
    }

    @Override
    public void updateStats() {

    }

    @Override
    public String getComponentName() {
        if (getHero().getToolBar().getBlockPouch().getCurrentBlockQuantity() > 0)
            return getHero().getToolBar().getBlockPouch().getCurrentBlockName();
        return "BlockPlacer";
    }
}
