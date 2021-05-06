package Model.items.food;

import Model.elements.Hero;

public class Banana extends Food {
    public Banana(Hero hero) {
        super(hero);
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getLevel().getNumLevel();
        int duration = Math.max(20 - heroLevel / 10, 3);
        this.setStats(new FoodStats(30, duration));
    }
}
