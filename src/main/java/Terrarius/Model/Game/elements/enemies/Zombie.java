package Terrarius.Model.Game.elements.enemies;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;

public class Zombie extends Enemy {
    static private final int ZOMBIE_VIEW_DISTANCE = 20;

    public Zombie(Position position, Level level) {
        super(position, new Dimensions(8, 4), level);
    }

    @Override
    public EnemyStats calculateStats(Level level) {
        int hp = 20 + level.getNumLevel();
        int power = 10 + level.getNumLevel();
        return new EnemyStats(hp, power, ZOMBIE_VIEW_DISTANCE, level);
    }

    @Override
    public String getComponentName() {
        return "Zombie";
    }
}
