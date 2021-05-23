package Terrarius.Model.items.food;

public class FoodStats {
    // food should heal contiguously during a time interval
    private final int amountHP;
    private final int duration;

    public FoodStats(int amountHP, int duration) {
        this.amountHP = amountHP;
        this.duration = duration;  // seconds
    }

    public int getAmountHP() {
        return amountHP;
    }

    public int getDuration() {
        return duration;
    }
}
