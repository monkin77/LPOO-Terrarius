package Model.elements.enemies;

import Model.Level;
import Model.Position;

public class Zombie extends Enemy {
    public Zombie(Position position, Level level) {
        super(position, level);
    }

    @Override
    int calculateHP(Level level) {
        return 10 + level.getNumLevel();
    }

    @Override
    int calculatePower(Level level) {
        return 2 + level.getNumLevel() / 5;
    }
}
