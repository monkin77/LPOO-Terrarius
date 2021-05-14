package Terrarius.Model.items.tools;

import Terrarius.Model.Dimensions;
import Terrarius.Model.elements.Hero;
import Terrarius.Model.items.Item;

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
