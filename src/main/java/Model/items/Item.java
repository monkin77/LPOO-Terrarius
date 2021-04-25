package Model.items;

import Model.elements.Hero;

public abstract class Item {
    public Item(Hero hero) {
        updateStats(hero);
    }

    // Item stats should be updated when hero level/stats are increased
    public abstract void updateStats(Hero hero);
}
