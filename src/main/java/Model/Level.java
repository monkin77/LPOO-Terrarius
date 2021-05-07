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

    protected static int getXpMultiplier() {
        return XP_MULTIPLIER;
    }

    public long getCurrentXP() {
        return currentXP;
    }

    public long getMaxXP() {
        return maxXP;
    }

    public int getNumLevel() {
        return numLevel;
    }

    void increaseXP(int xp) {
        this.currentXP += xp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Level level = (Level) obj;
        return level.getNumLevel() == this.getNumLevel() &&
                level.getCurrentXP() == this.getCurrentXP();
    }
}
