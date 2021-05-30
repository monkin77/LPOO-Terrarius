package Terrarius.Model.Game.elements.hero;

import Terrarius.Model.Game.Level;
import Terrarius.Model.SkillTree.Skills.*;

import java.util.Arrays;
import java.util.List;

public class HeroStats {
    private static final double DEFAULT_RANGE = 24.0;
    private static final int START_HP = 100;
    private static final int DEFAULT_SPEED = 1;
    private static final int DEFAULT_POWER = 1;

    private Level level;
    private int hp;
    private int maxHP;
    private int power;
    private int speed;
    private double range;

    private int usedPoints;
    private List<Skill> skills;

    public HeroStats() {
        this.level = new Level(1, 0);
        this.hp = START_HP;
        this.maxHP = START_HP;
        this.power = DEFAULT_POWER;
        this.speed = DEFAULT_SPEED;
        this.range = DEFAULT_RANGE;
        this.usedPoints = 0;
        this.skills = Arrays.asList(new AttackSkill(), new HealthSkill(), new RangeSkill(), new SpeedSkill());
    }

    public HeroStats(Level level, int hp, int power, int speed, double range) {
        this.level = level;
        this.hp = hp;
        this.maxHP = START_HP;
        this.power = power;
        this.speed = speed;
        this.range = range;
        this.usedPoints = 0;
        this.skills = Arrays.asList(new AttackSkill(), new HealthSkill(), new RangeSkill(), new SpeedSkill());
    }

    public Level getLevel() {
        return level;
    }

    public int getCurrentLevel() {
        return level.getNumLevel();
    }

    public int getHp() {
        return hp;
    }

    public int getPower() {
        return power;
    }

    public int getSpeed() {
        return speed;
    }

    public double getRange() {
        return range;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setHP(int hp) {
        if (hp > this.maxHP) hp = maxHP;
        this.hp = hp;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getUsedPoints() {
        return usedPoints;
    }

    public void setUsedPoints(int usedPoints) {
        this.usedPoints = usedPoints;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Fetch the available points to use
     * @return available points
     */
    public int getCurrentPoints(){
        return this.getCurrentLevel() * 10 - this.usedPoints;
    }

}
