package Terrarius.Model.Game.items.buffs;

public class BuffStats {
    private final int duration;
    private final int amountHP;
    private final int attack;
    private final int speedUp;
    private final int extraRange;

    public BuffStats(int duration, int amountHP, int attack, int speedUp, int extraRange) {
        this.duration = duration;
        this.amountHP = amountHP;
        this.attack = attack;
        this.speedUp = speedUp;
        this.extraRange = extraRange;
    }

    public int getAmountHP() {
        return amountHP;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpeedUp() {
        return speedUp;
    }
}
