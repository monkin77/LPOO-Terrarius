package Terrarius.Model.Game.elements.enemies;

import Terrarius.Model.Game.Level;

public class EnemyStats {
    private int hp;
    private final int power;
    private final int viewDistance;
    private final Level level;

    public EnemyStats(int hp, int power, int viewDistance, Level level) {
        this.hp = hp;
        this.power = power;
        this.viewDistance = viewDistance;
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public int getPower() {
        return power;
    }

    public int getViewDistance() {
        return viewDistance;
    }

    public Level getLevel() {
        return level;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
