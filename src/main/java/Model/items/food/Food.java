package Model.items.food;

import Model.elements.Hero;
import Model.items.Item;

public abstract class Food extends Item {
    private FoodStats stats;

    public Food(Hero hero) {
        super(hero);
    }

    public FoodStats getStats() {
        return stats;
    }

    public void setStats(FoodStats stats) {
        this.stats = stats;
    }
}
