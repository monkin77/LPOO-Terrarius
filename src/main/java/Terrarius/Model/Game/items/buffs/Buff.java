package Terrarius.Model.Game.items.buffs;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.elements.Hero;
import Terrarius.Model.Game.items.Item;

public abstract class Buff extends Item {
    private BuffStats stats;

    public Buff(Hero hero, Dimensions dimensions) {
        super(hero, dimensions);
    }

    public BuffStats getStats() {
        return stats;
    }

    public void setStats(BuffStats stats) {
        this.stats = stats;
    }
}
