package Terrarius.Model.SkillTree;

import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.MenuTemplate;
import Terrarius.Model.SkillTree.Skills.*;

import java.util.Arrays;
import java.util.List;

public class SkillTree extends MenuTemplate<Skill> {
    private int usedPoints = 0;
    private final HeroStats heroStats;

    public SkillTree(HeroStats heroStats) {
        super();
        this.heroStats = heroStats;
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

    @Override
    protected List<Skill> initOptions() {
        return Arrays.asList(new AttackSkill(), new HealthSkill(), new RangeSkill(), new SpeedSkill());
    }
}
