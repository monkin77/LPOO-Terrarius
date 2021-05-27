package Terrarius.Model.SkillTree.Skills;

public abstract class Skill {
    private String name;
    private int currLevel;
    private static int maxLevel = 10;

    public Skill(String name) {
        this.name = name;
        this.currLevel = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrLevel() {
        return currLevel;
    }

    public void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
    }

    public static int getMaxLevel() {
        return maxLevel;
    }
}
