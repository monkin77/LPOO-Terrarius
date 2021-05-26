package Terrarius.Model.Game.items.buffs;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Utils.Dimensions;

public class SwiftnessPotion extends Buff {
    public SwiftnessPotion(Hero hero) {
        super(hero, new Dimensions(4, 3));
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getStats().getCurrentLevel();
        int speed = 1 + heroLevel / 50;
        int duration = 10 + heroLevel / 15;
        this.setStats(new BuffStats(duration, 0, 0, speed, 0));
    }

    @Override
    public String getComponentName() {
        return "SwiftnessPotion";
    }
}
