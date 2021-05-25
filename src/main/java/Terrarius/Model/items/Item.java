package Terrarius.Model.items;

import Terrarius.Model.Game.elements.Hero;
import Terrarius.Utils.Dimensions;

public abstract class Item {
    private Hero hero;
    private Dimensions dimensions;

    public Item(Hero hero, Dimensions dimensions) {
        this.hero = hero;
        this.dimensions = dimensions;
        updateStats();
    }

    public Hero getHero() {
        return hero;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    // Item stats should be updated when hero level/stats are increased
    public abstract void updateStats();
}
