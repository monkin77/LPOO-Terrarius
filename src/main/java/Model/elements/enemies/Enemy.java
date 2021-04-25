package Model.elements.enemies;

import Model.Level;
import Model.Position;
import Model.elements.Element;

public abstract class Enemy extends Element {
    private EnemyStats stats;

    public Enemy(Position position, Level level) {
        super(position);
        this.stats = new EnemyStats(calculateHP(level), calculatePower(level), level);
    }

    // In the future, it might be calculateStats with an extra class
    abstract int calculateHP(Level level);
    abstract int calculatePower(Level level);
}
