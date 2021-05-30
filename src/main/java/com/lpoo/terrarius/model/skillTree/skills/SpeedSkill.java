package com.lpoo.terrarius.model.skillTree.skills;

import com.lpoo.terrarius.model.game.elements.hero.HeroStats;

public class SpeedSkill extends Skill {
    public SpeedSkill() {
        super("Speed", 100);
    }

    @Override
    public void applyEffect(HeroStats stats) {
        stats.setSpeed(stats.getSpeed() + 1);
    }
}
