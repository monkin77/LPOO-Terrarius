package com.lpoo.terrarius.model.skillTree.skills;

import com.lpoo.terrarius.model.game.elements.hero.HeroStats;

public abstract class Skill {
    private final String name;
    private int currLevel;
    private static final int maxLevel = 10;
    private final int upgradeCost;

    public Skill(String name, int upgradeCost) {
        this.name = name;
        this.upgradeCost = upgradeCost;
        this.currLevel = 0;
    }

    public String getName() {
        return name;
    }

    public int getCurrLevel() {
        return currLevel;
    }

    public static int getMaxLevel() {
        return maxLevel;
    }

    public int getUpgradeCost() {
        return upgradeCost;
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
