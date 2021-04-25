package Model.items.tools;

import Model.elements.Hero;

public class Shovel extends Tool {
    public Shovel(Hero hero) {
        super(hero);
    }

    @Override
    public void updateStats(Hero hero) {
        int heroLevel = hero.getLevel().getNumLevel();
        this.setStats(new ToolStats(1, 10, 1));
    }
}
