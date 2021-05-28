package Terrarius.Model.SkillTree;

import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.SkillTree.Skills.*;

import java.util.Arrays;
import java.util.List;

public class SkillTree {
    private int selected = 0;
    private int usedPoints = 0;

    private List<Skill> skills;
    private HeroStats heroStats;

    public SkillTree(HeroStats heroStats) {
        this.heroStats = heroStats;
        this.skills = Arrays.asList(new AttackSkill(), new HealthSkill(), new RangeSkill(), new SpeedSkill());
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void nextSkill() {
        this.selected = (this.selected + 1) % skills.size();
    }

    public void previousSkill() {
        int nextSel = this.selected - 1;
        while(nextSel < 0) nextSel += skills.size();
        this.selected = nextSel % skills.size();
    }

    public int getUsedPoints() {
        return usedPoints;
    }

    public void setUsedPoints(int usedPoints) {
        this.usedPoints = usedPoints;
    }

    public int getHeroLevel() {
        return this.heroStats.getCurrentLevel();
    }

    public int getAvailablePoints() {
        return this.getHeroLevel() - this.usedPoints;
    }

    public void upgradeSkill(Skill selSkill) {
        if( this.getAvailablePoints() >= selSkill.getUpgradeCost()) {
            if (selSkill.upgrade()) {
                this.setUsedPoints(this.getUsedPoints() + selSkill.getUpgradeCost());
                selSkill.applyEffect(this.heroStats);
            }
        }
    }
}
