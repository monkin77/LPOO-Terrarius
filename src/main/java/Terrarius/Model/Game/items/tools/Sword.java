package Terrarius.Model.Game.items.tools;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.elements.hero.Hero;

public class Sword extends Tool {
    public Sword(Hero hero) {
        super(hero, new Dimensions(4, 2));
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getStats().getCurrentLevel();
        this.setStats(new ToolStats(heroLevel / 2 + 1, 0, 0));
    }

    @Override
    public String getComponentName() {
        return "Sword";
    }
}
