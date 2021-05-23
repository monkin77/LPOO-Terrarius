package Terrarius.Model.elements.enemies;

import Terrarius.Model.Dimensions;
import Terrarius.Model.Level;
import Terrarius.Model.Position;
import Terrarius.Model.elements.Element;

public abstract class Enemy extends Element {
    private EnemyStats stats;

    public Enemy(Position position, Dimensions dimensions, Level level) {
        super(position, dimensions);
        this.stats = new EnemyStats(calculateHP(level), calculatePower(level), level);
    }

    public EnemyStats getStats() {
        return stats;
    }

    abstract int calculateHP(Level level);
    abstract int calculatePower(Level level);
}
