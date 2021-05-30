package com.lpoo.terrarius.model.game.items;

import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.utils.Dimensions;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class BlockPlacer extends Item {
    public BlockPlacer(Hero hero) throws FileNotFoundException, URISyntaxException {
        super(hero, "BlockPlacer");
    }

    @Override
    protected void loadItem() {
        this.setDimensions(new Dimensions(3, 3));
    }

    @Override
    public String getComponentName() {
        if (getHero().getToolBar().getBlockPouch().getCurrentBlockQuantity() > 0)
            return getHero().getToolBar().getBlockPouch().getCurrentBlockName();
        return super.getComponentName();
    }

    @Override
    public void updateStats() {
        // No Stats
    }
}
