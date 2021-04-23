package Model;

public class Level {
    private static final int XP_MULTIPLIER = 1000;

    private long currentXP;
    private final long maxXP;
    private final int numLevel;

    public Level(int numLevel, long startXP) {
        this.numLevel = numLevel;
        this.currentXP = startXP;  // we may xp carrying from the previous level
        this.maxXP = numLevel * XP_MULTIPLIER;
    }

    public long getCurrentXP() {
        return currentXP;
    }

    public long getMaxXP() {
        return maxXP;
    }

    public int getLevel() {
        return numLevel;
    }

    // Consider using Observer Pattern
    boolean increaseXP(int xp) {
        this.currentXP += xp;
        return this.currentXP > maxXP;
    }
}
