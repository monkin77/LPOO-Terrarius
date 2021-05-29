package Terrarius.Model.SkillTree;

import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.SkillTree.Skills.*;

import java.util.List;

public class SkillTree {
    private int selectedIndex = 0;

    private HeroStats heroStats;

    public SkillTree(HeroStats heroStats) {
        this.heroStats = heroStats;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public List<Skill> getSkills() {
        return this.heroStats.getSkills();
    }

    public void setSkills(List<Skill> skills) {
        this.heroStats.setSkills(skills);
    }

    public void nextOption() {
        this.selectedIndex = (this.selectedIndex + 1) % getSkills().size();
    }

    public void previousOption() {
        int nextSel = this.selectedIndex - 1;
        while(nextSel < 0) nextSel += getSkills().size();
        this.selectedIndex = nextSel % getSkills().size();
    }

    public int getUsedPoints() {
        return this.heroStats.getUsedPoints();
    }

    public void setUsedPoints(int usedPoints) {
        this.heroStats.setUsedPoints(usedPoints);
    }

    public int getHeroLevel() {
        return this.heroStats.getCurrentLevel();
    }

    public HeroStats getHeroStats() {
        return heroStats;
    }

    public void upgradeSkill(Skill selSkill) {
        if( this.heroStats.getCurrentPoints() >= selSkill.getUpgradeCost()) {
            if (selSkill.upgrade()) {
                this.setUsedPoints(this.getUsedPoints() + selSkill.getUpgradeCost());
                selSkill.applyEffect(this.heroStats);
            }
        }
    }
}