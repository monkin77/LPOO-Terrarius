package Model.items.tools;

import Model.Dimensions;
import Model.elements.Hero;

public class Axe extends Tool {
    public Axe(Hero hero) {
        super(hero, new Dimensions(4, 2));
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getLevel().getNumLevel();
        this.setStats(new ToolStats(1 + heroLevel / 5, 10 + heroLevel / 5, 2));
    }
}
