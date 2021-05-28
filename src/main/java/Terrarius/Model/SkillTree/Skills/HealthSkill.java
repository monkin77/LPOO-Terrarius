package Terrarius.Model.SkillTree.Skills;

import Terrarius.Model.Game.elements.hero.HeroStats;

public class HealthSkill extends Skill{
    public HealthSkill() {
        super("Health", 2);
    }

    @Override
    public void applyEffect(HeroStats stats) {
        stats.setMaxHP((int) (stats.getMaxHP() * 1.25));
    }
}
