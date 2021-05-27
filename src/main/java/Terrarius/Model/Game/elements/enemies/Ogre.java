package Terrarius.Model.Game.elements.enemies;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Utils.Dimensions;

public class Ogre extends Enemy{
    static private final int OGRE_VIEW_DISTANCE = 30;

    public Ogre(Position position, Level level) {
        super(position, new Dimensions(12, 12), level);
    }

    @Override
    public String getComponentName() {
        return "Ogre";
    }

    @Override
    EnemyStats calculateStats(Level level) {
        int hp = 50 + 2*level.getNumLevel();
        int power = 30 + 2*level.getNumLevel();
        return new EnemyStats(hp, power, OGRE_VIEW_DISTANCE, level);
    }
}
