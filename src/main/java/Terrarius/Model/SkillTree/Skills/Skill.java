package Terrarius.Model.SkillTree.Skills;

import Terrarius.Model.Game.elements.hero.HeroStats;

public abstract class Skill {
    private String name;
    private int currLevel;
    private static final int maxLevel = 10;
    private int upgradeCost;

    public Skill(String name, int upgradeCost) {
        this.name = name;
        this.upgradeCost = upgradeCost;
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

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public boolean upgrade() {
        if(currLevel == maxLevel) {
            return false;
        }
        currLevel++;
        return true;
    }

    public abstract void applyEffect(HeroStats stats);
}
