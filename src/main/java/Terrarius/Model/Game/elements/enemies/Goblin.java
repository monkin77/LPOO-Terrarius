package Terrarius.Model.Game.elements.enemies;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Utils.Dimensions;

public class Goblin extends Enemy{

    static private final int GOBLIN_VIEW_DISTANCE = 40;

    public Goblin(Position position, Level level) {
        super(position, new Dimensions(6, 3), level);
    }

    @Override
    public String getComponentName() {
        return "Goblin";
    }

    @Override
    EnemyStats calculateStats(Level level) {
        int hp = 10 + level.getNumLevel();
        int power = 15 + level.getNumLevel();
        return new EnemyStats(hp, power, GOBLIN_VIEW_DISTANCE, level);
    }
}
