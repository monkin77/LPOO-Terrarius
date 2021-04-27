package Model.items.tools;

import Model.elements.Hero;
import Model.items.Item;

public abstract class Tool extends Item {
    private ToolStats stats;

    public Tool(Hero hero) {
        super(hero);
    }

    public ToolStats getStats() {
        return stats;
    }

    public void setStats(ToolStats stats) {
        this.stats = stats;
    }
}
