package Terrarius.Model.Game.elements.enemies;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;

public abstract class Enemy extends Element {
    private EnemyStats stats;

    public Enemy(Position position, Dimensions dimensions, Level level) {
        super(position, dimensions);
        this.stats = calculateStats(level);
    }

    public EnemyStats getStats() {
        return stats;
    }

    abstract EnemyStats calculateStats(Level level);
}
