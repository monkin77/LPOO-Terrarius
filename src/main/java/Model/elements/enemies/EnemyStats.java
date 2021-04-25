package Model.elements.enemies;

import Model.Level;

public class EnemyStats {
    private int hp;
    private int power;
    private Level level;

    public EnemyStats(int hp, int power, Level level) {
        this.hp = hp;
        this.power = power;
        this.level = level;
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
}
