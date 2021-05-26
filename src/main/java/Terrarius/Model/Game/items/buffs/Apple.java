package Terrarius.Model.Game.items.buffs;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.elements.hero.Hero;

public class Apple extends Buff {
    public Apple(Hero hero) {
        super(hero, new Dimensions(3, 3));
    }

    @Override
    public void updateStats() {
        this.setStats(new BuffStats(5, 10, 0, 0, 0));
    }

    @Override
    public String getComponentName() {
        return "Apple";
    }
}
