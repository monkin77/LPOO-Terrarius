package Terrarius.Model.elements.enemies;

import Terrarius.Model.Dimensions;
import Terrarius.Model.Level;
import Terrarius.Model.Position;

public class Zombie extends Enemy {
    static private final int ZOMBIE_VIEW_DISTANCE = 20;

    public Zombie(Position position, Level level) {
        super(position, new Dimensions(8, 4), level);
    }

    @Override
    public EnemyStats calculateStats(Level level) {
        int hp = 10 + level.getNumLevel();
        int power = 2 + level.getNumLevel() / 5;
        return new EnemyStats(hp, power, ZOMBIE_VIEW_DISTANCE, level);
    }
}
