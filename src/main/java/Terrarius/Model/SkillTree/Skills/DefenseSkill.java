package Terrarius.Model.SkillTree.Skills;

import Terrarius.Model.Game.elements.hero.HeroStats;

public class DefenseSkill extends Skill {
    public DefenseSkill() {
        super("Defense", 1);
    }

    @Override
    public void applyEffect(HeroStats stats) {
        // Temporary since we don't have defense stat
        stats.setRange(stats.getRange() + 3);
    }
}
