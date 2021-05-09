package Model.items.food;

import Model.Dimensions;
import Model.elements.Hero;
import Model.items.Item;

public abstract class Food extends Item {
    private FoodStats stats;

    public Food(Hero hero, Dimensions dimensions) {
        super(hero, dimensions);
    }

    public FoodStats getStats() {
        return stats;
    }

    public void setStats(FoodStats stats) {
        this.stats = stats;
    }
}
