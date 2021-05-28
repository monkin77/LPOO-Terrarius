package Terrarius.Model.Game.elements.enemies;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;

import java.util.Objects;

public abstract class Enemy extends Element {
    private final EnemyStats stats;

    public Enemy(Position position, Dimensions dimensions, Level level) {
        super(position, dimensions);
        this.stats = calculateStats(level);
    }

    public EnemyStats getStats() {
        return stats;
    }
    abstract EnemyStats calculateStats(Level level);
    public void setHP(int hp) {this.stats.setHp(hp);}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Enemy enemy2 = (Enemy) obj;
        return this.getComponentName().equals(enemy2.getComponentName())
                && this.stats.equals(enemy2.getStats())
                && getPosition().equals(enemy2.getPosition())
                && getDimensions().equals(enemy2.getDimensions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(stats.getLevel(), stats.getPower(), stats.getViewDistance(),
                            getComponentName(), getDimensions());
    }
}
