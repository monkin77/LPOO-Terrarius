package Terrarius.Model.Game.items.tools;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.elements.hero.Hero;

public class Axe extends Tool {
    public Axe(Hero hero) {
        super(hero, new Dimensions(5, 3));
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getStats().getCurrentLevel();
        this.setStats(new ToolStats(1 + heroLevel / 5, 10 + heroLevel / 5, 2));
    }
}
