package Terrarius.Model.SkillTree.Skills;

import Terrarius.Model.Game.elements.hero.HeroStats;

public class AttackSkill extends Skill {
    public AttackSkill() {
        super("Attack", 10);
    }

    @Override
    public void applyEffect(HeroStats stats) {
        stats.setPower(stats.getPower() + 3);
    }
}
