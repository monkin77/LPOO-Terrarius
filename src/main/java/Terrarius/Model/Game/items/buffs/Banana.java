package Terrarius.Model.Game.items.buffs;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.elements.Hero;

public class Banana extends Buff {
    public Banana(Hero hero) {
        super(hero, new Dimensions(2, 2));
    } // Could be changed in the future

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getLevel().getNumLevel();
        this.setStats(new BuffStats(0, 5 + heroLevel / 5, 0, 0, 0));
    }
}
