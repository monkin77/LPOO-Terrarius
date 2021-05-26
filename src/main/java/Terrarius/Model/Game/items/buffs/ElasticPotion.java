package Terrarius.Model.Game.items.buffs;

import Terrarius.Model.Game.elements.Hero;
import Terrarius.Utils.Dimensions;

public class ElasticPotion extends Buff {
    public ElasticPotion(Hero hero) {
        super(hero, new Dimensions(2, 2));
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getLevel().getNumLevel();
        int duration = 10 + heroLevel / 3;
        int range = 2 + heroLevel / 10;
        this.setStats(new BuffStats(duration, 0, 0, 0, range));
    }
}
