package Model.items;

import Model.elements.Hero;

public abstract class Item {
    protected Hero hero;

    public Item(Hero hero) {
        this.hero = hero;
        updateStats();
    }

    public Hero getHero() {
        return hero;
    }

    // Item stats should be updated when hero level/stats are increased
    public abstract void updateStats();
}
