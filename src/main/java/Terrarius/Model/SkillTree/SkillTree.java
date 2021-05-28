package Terrarius.Model.SkillTree;

import Terrarius.Model.Game.Level;
import Terrarius.Model.SkillTree.Skills.AttackSkill;
import Terrarius.Model.SkillTree.Skills.DefenseSkill;
import Terrarius.Model.SkillTree.Skills.Skill;
import Terrarius.Model.SkillTree.Skills.SpeedSkill;

import java.util.Arrays;
import java.util.List;

public class SkillTree {
    private int selected = 0;
    private int usedPoints = 0;

    List<Skill> skills;
    int heroLevel;

    public SkillTree(Level heroLevel) {
        this.heroLevel = heroLevel.getNumLevel();
        this.skills = Arrays.asList(new AttackSkill(), new DefenseSkill(), new SpeedSkill());
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void nextSkill() {
        this.selected = (this.selected + 1) % skills.size();
    }

    public void previousSkill() {
        int nextSel = this.selected - 1;
        while(nextSel < 0) nextSel += skills.size();
        this.selected = nextSel % skills.size();
    }

    public int getUsedPoints() {
        return usedPoints;
    }

    public void setUsedPoints(int usedPoints) {
        this.usedPoints = usedPoints;
    }

    public int getHeroLevel() {
        return heroLevel;
    }

    public int getAvailablePoints() {
        return this.heroLevel - this.usedPoints;
    }


}
