package Terrarius.Model.Game.items.buffs;

import Terrarius.Model.Game.elements.Hero;
import Terrarius.Utils.Dimensions;

public class SwiftnessPotion extends Buff {
    public SwiftnessPotion(Hero hero) {
        super(hero, new Dimensions(2, 2));
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getLevel().getNumLevel();
        int speed = 5 + heroLevel / 50;
        int duration = 10 + heroLevel / 15;
        this.setStats(new BuffStats(duration, 0, 0, speed, 0));
    }
}
