package Model.elements;

import Model.Dimensions;
import Model.Level;
import Model.Position;

public class Hero extends Element {
    private Level level;
    private int health;

    public Hero(Position position) {
        super(position, new Dimensions(8, 4));
        this.level = new Level(1, 0);
        this.health = 100;
    }

    public Level getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // TO DO: ITEMS LOGIC (maybe limit to X items)
}
