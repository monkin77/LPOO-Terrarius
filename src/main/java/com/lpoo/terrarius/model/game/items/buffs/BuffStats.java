package com.lpoo.terrarius.model.game.items.buffs;

public class BuffStats {
    private int duration;
    private final int amountHP;
    private final int power;
    private final int speedUp;
    private final int extraRange;

    public BuffStats(int duration, int amountHP, int power, int speedUp, int extraRange) {
        this.duration = duration;
        this.amountHP = amountHP;
        this.power = power;
        this.speedUp = speedUp;
        this.extraRange = extraRange;
    }

    public int getAmountHP() {
        return amountHP;
    }

    public int getPower() {
        return power;
    }

    public int getSpeedUp() {
        return speedUp;
    }

    public int getDuration() {
        return duration;
    }

    public int getExtraRange() {
        return extraRange;
    }

    public void decreaseDuration() {
        this.duration--;
    }
}
