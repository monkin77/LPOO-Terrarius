package Model.items.tools;

import Model.elements.Hero;

public class Sword extends Tool {
    public Sword(Hero hero) {
        super(hero);
    }

    @Override
    public void updateStats(Hero hero) {
        int heroLevel = hero.getLevel().getNumLevel();
        this.setStats(new ToolStats(heroLevel / 2 + 1, 0, 0));
    }
}
