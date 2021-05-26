package Terrarius.Model.Game.items.buffs;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.elements.hero.Hero;

public class Banana extends Buff {
    public Banana(Hero hero) {
        super(hero, new Dimensions(2, 2));
    } // Could be changed in the future

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getStats().getCurrentLevel();
        int hp = 5 + heroLevel / 5;
        this.setStats(new BuffStats(hp / 2, hp, 0, 0, 0));
    }
}
