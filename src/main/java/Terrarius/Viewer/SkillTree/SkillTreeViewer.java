package Terrarius.Viewer.SkillTree;

import Terrarius.GUI.GUI;
import Terrarius.Model.SkillTree.SkillTree;
import Terrarius.Model.SkillTree.Skills.Skill;
import Terrarius.Viewer.Viewer;

import java.io.IOException;

public class SkillTreeViewer extends Viewer<SkillTree> {

    @Override
    public void draw(GUI gui, SkillTree model) throws IOException {
        // For now the screen is flickering too fast

        gui.clear();

        gui.drawString(20, 20, "Skill Tree", "#FFFFFF", "#000000");


        for(int i = 0; i < model.getSkills().size(); i++) {
            Skill skill = model.getSkills().get(i);
            gui.drawString(20, 20 + (i + 1) * 10, skill.getName(), "#FFFFFF", "#000000");
        }

        gui.refresh();
    }

    @Override
    public void update() {

    }
}
