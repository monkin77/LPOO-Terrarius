package com.lpoo.terrarius.model.skillTree.skills;

import com.lpoo.terrarius.model.game.elements.hero.HeroStats;

public class RangeSkill extends Skill {
    public RangeSkill() {
        super("Range", 50);
    }

    @Override
    public void applyEffect(HeroStats stats) {
        stats.setRange(stats.getRange() + 1);
    }
}
