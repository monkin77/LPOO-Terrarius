package Terrarius.Model.Game.items.tools;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.Item;

public abstract class Tool extends Item {
    private ToolStats stats;

    public Tool(Hero hero, Dimensions dimensions) {
        super(hero, dimensions);
    }

    public ToolStats getStats() {
        return stats;
    }

    public void setStats(ToolStats stats) {
        this.stats = stats;
    }
}
