package Terrarius.Model.Game.items.buffs;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Utils.Dimensions;

public class BattlePotion extends Buff {
    public BattlePotion(Hero hero) {
        super(hero, new Dimensions(4, 3));
    }

    @Override
    public void updateStats() {
        int heroLevel = this.getHero().getStats().getCurrentLevel();
        int duration = 10 + heroLevel / 5;
        int attack = 2 + heroLevel / 5;
        this.setStats(new BuffStats(duration, 0, attack, 0, 0));
    }

    @Override
    public String getComponentName() {
        return "BattlePotion";
    }
}
