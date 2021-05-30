package Terrarius.Model.Game;

public class Level {
    private static final int XP_MULTIPLIER = 1000;

    private long currentXP;
    private long maxXP;
    private int numLevel;

    public Level(int numLevel, long startXP) {
        this.numLevel = numLevel;
        this.currentXP = startXP;  // xp may carry from the previous level
        this.maxXP = (long) numLevel * XP_MULTIPLIER;
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

    public void increaseXP(long xp) {
        this.currentXP += xp;
        this.tryLevelUp();
    }

    private void tryLevelUp(){
        if (this.currentXP >= maxXP){
            this.currentXP -= maxXP;
            this.numLevel++;
            this.maxXP = (long) numLevel * XP_MULTIPLIER;
            this.tryLevelUp();
        }
    }

    public long calcXpDrop(){
        long res = currentXP;
        for (int i = 1; i <= this.numLevel; i++){
            res += (long) XP_MULTIPLIER * i;
        }
        return res;
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
