package Model.elements.enemies;

import Model.Dimensions;
import Model.Level;
import Model.Position;
import Model.elements.Element;

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
