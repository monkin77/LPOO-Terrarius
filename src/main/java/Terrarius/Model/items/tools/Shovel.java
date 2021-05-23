package Terrarius.Model.items.tools;

import Terrarius.Model.Dimensions;
import Terrarius.Model.elements.Hero;

public class Shovel extends Tool {
    public Shovel(Hero hero) {
        super(hero, new Dimensions(4, 2));
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getLevel().getNumLevel();
        this.setStats(new ToolStats(1 + heroLevel / 20, 10, 1));
    }
}
