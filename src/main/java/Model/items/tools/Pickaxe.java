package Model.items.tools;

import Model.Dimensions;
import Model.elements.Hero;

public class Pickaxe extends Tool {
    public Pickaxe(Hero hero) {
        super(hero, new Dimensions(4, 2));
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getLevel().getNumLevel();
        this.setStats(new ToolStats(2, 10 + heroLevel / 10, 3));
    }
}
