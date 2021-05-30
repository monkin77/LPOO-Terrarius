package Terrarius.Model.SkillTree;

import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.MenuTemplate;
import Terrarius.Model.SkillTree.Skills.*;

import java.util.ArrayList;
import java.util.List;

public class SkillTree extends MenuTemplate<Skill> {
    private HeroStats heroStats;

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