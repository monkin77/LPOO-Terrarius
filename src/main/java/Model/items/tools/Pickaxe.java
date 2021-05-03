package Model.items.tools;

import Model.elements.Hero;

public class Pickaxe extends Tool {
    public Pickaxe(Hero hero) {
        super(hero);
    }

    @Override
    public void updateStats() {
        int heroLevel = this.hero.getLevel().getNumLevel();
        this.setStats(new ToolStats(2, 10 + heroLevel / 10, 3));
    }
}
