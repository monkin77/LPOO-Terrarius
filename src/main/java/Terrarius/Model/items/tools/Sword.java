package Terrarius.Model.items.tools;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.elements.Hero;

public class Sword extends Tool {
    public Sword(Hero hero) {
        super(hero, new Dimensions(4, 2));
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getLevel().getNumLevel();
        this.setStats(new ToolStats(heroLevel / 2 + 1, 0, 0));
    }
}
