package Terrarius.Model.SkillTree.Skills;

public abstract class Skill {
    private String name;
    private int level;

    public Skill(String name) {
        this.name = name;
        this.level = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
