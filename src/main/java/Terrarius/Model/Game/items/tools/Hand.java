package Terrarius.Model.Game.items.tools;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Utils.Dimensions;

public class Hand extends Tool {
    public Hand(Hero hero) {
        super(hero, new Dimensions(0, 0));
    }

    @Override
    public void updateStats() {
        this.setStats(new ToolStats(0, 1, 1));
    }

    @Override
    public String getComponentName() {
        return "Hand";
    }
}
