package Model.elements.enemies;

import Model.Level;
import Model.Position;
import Model.elements.Element;

public abstract class Enemy extends Element {
    private int hp;
    private int power;
    private Level level;

    public Enemy(Position position, Level level) {
        super(position);
        this.level = level;
        this.hp = calculateHP(level);
        this.power = calculatePower(level);
    }

    public int getHp() {
        return hp;
    }

    public int getPower() {
        return power;
    }

    public Level getLevel() {
        return level;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    // In the future, it might be calculateStats with an extra class
    abstract int calculateHP(Level level);
    abstract int calculatePower(Level level);
}
