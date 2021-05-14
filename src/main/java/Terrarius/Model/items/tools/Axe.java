package Terrarius.Model.items.tools;

import Terrarius.Model.Dimensions;
import Terrarius.Model.elements.Hero;

public class Axe extends Tool {
    public Axe(Hero hero) {
        super(hero, new Dimensions(5, 3));
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getLevel().getNumLevel();
        this.setStats(new ToolStats(1 + heroLevel / 5, 10 + heroLevel / 5, 2));
    }
}
