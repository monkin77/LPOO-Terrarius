package Terrarius.Viewer.SkillTree;

import Terrarius.GUI.GUI;
import Terrarius.Model.SkillTree.SkillTree;
import Terrarius.Viewer.Viewer;

import java.io.IOException;

public class SkillTreeViewer extends Viewer<SkillTree> {

    @Override
    public void draw(GUI gui, SkillTree model) throws IOException {
        // For now the screen is flickering too fast

        gui.clear();

        gui.drawString(20, 20, "Skill Tree", "#FFFFFF", "#000000");
        gui.drawCharacter(1, 1, 'F');

        gui.refresh();
    }

    @Override
    public void update() {

    }
}
