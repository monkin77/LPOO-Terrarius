package com.lpoo.terrarius.model.skillTree;

import com.lpoo.terrarius.model.game.Level;
import com.lpoo.terrarius.model.game.elements.hero.HeroStats;
import com.lpoo.terrarius.model.skillTree.skills.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SkillTest {
    private HeroStats stats;
    private Skill skill;

    @BeforeEach
    public void setup() {
        stats = new HeroStats(new Level(1, 0), 100, 5, 1, 7);
    }

    @Test
    public void healthSkill() {
        skill = new HealthSkill();
        skill.applyEffect(stats);
        Assertions.assertEquals(125, stats.getMaxHP());
    }

    @Test
    public void speedSkill() {
        skill = new SpeedSkill();
        skill.applyEffect(stats);
        Assertions.assertEquals(2, stats.getSpeed());
    }

    @Test
    public void attackSkill() {
        skill = new AttackSkill();
        skill.applyEffect(stats);
        Assertions.assertEquals(8, stats.getPower());
    }

    @Test
    public void rangeSkill() {
        skill = new RangeSkill();
        skill.applyEffect(stats);
        Assertions.assertEquals(8, stats.getRange());
    }
}
