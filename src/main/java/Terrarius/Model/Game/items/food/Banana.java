package Terrarius.Model.Game.items.food;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.elements.Hero;

public class Banana extends Food {
    public Banana(Hero hero) {
        super(hero, new Dimensions(2, 2));
    } // Could be changed in the future

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getLevel().getNumLevel();
        int duration = Math.max(20 - heroLevel / 10, 3);
        this.setStats(new FoodStats(30, duration));
    }
}
