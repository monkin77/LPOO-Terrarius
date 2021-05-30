package com.lpoo.terrarius.model.game.elements.enemies;

import com.lpoo.terrarius.model.game.Level;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        EnemyStats stats = (EnemyStats) obj;
        return this.level.equals(stats.getLevel())
                && this.hp == stats.getHp()
                && this.viewDistance == stats.getViewDistance()
                && this.power == stats.getPower();
    }
}
