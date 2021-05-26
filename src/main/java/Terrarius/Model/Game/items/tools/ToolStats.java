package Terrarius.Model.Game.items.tools;

public class ToolStats {
    private final int fightingPower;
    private final int miningPower;
    private final int miningHardness;

    public ToolStats(int fightingPower, int miningPower, int miningHardness) {
        this.fightingPower = fightingPower;
        this.miningPower = miningPower;
        this.miningHardness = miningHardness;
    }

    public int getFightingPower() {
        return fightingPower;
    }

    public int getMiningPower() {
        return miningPower;
    }

    public int getMiningHardness() {
        return miningHardness;
    }
}
