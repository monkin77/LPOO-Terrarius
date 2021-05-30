package com.lpoo.terrarius.model.skillTree;

import com.lpoo.terrarius.model.game.elements.hero.HeroStats;
import com.lpoo.terrarius.model.MenuTemplate;
import com.lpoo.terrarius.model.skillTree.skills.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillTree extends MenuTemplate<Skill> {
    private final HeroStats heroStats;

    public SkillTree(HeroStats heroStats) {
        super();
        this.heroStats = heroStats;
        this.getOptions().addAll(this.heroStats.getSkills());
    }

    public int getUsedPoints() {
        return this.heroStats.getUsedPoints();
    }

    public void setUsedPoints(int usedPoints) {
        this.heroStats.setUsedPoints(usedPoints);
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

    @Override
    protected List<Skill> initOptions() {
        return new ArrayList<>();
    }
}