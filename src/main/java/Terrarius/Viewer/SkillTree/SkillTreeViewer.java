package Terrarius.Viewer.SkillTree;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.SkillTree.SkillTree;
import Terrarius.Model.SkillTree.Skills.Skill;
import Terrarius.Viewer.Game.GameViewerConstants;
import Terrarius.Viewer.Image.ColoredImage;
import Terrarius.Viewer.Image.Image;
import Terrarius.Viewer.Viewer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class SkillTreeViewer extends Viewer<SkillTree> {
    private ColoredImage image;
    int previousSelected = -1;

    public SkillTreeViewer() {
        super();
        image = new ColoredImage();
        try {
            image.load("Images/SkillTree/SkillTree.txt");
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
            image = null;
        }
    }

    @Override
    public void draw(GUI gui, SkillTree model) throws IOException {
        if (previousSelected == model.getSelected()) return;
        previousSelected = model.getSelected();
        // For now the screen is flickering too fast

        gui.clear();

        String title = "Skill Tree";
        gui.drawString((GameViewerConstants.SCREEN_WIDTH - title.length()) /2, 5, title, "#FFFFFF", "#000000");

        Position treePos = new Position(50, 10);
        this.image.draw(treePos, Element.Orientation.RIGHT, gui);

        this.drawSkillsLabels(treePos, gui, model);

        int yOffset = 5;
        for(int i = 0; i < model.getSkills().size(); i++) {
            Skill skill = model.getSkills().get(i);
            String skillName = skill.getName() + ":";
            String skillLevel = skill.getCurrLevel() + " / " + skill.getMaxLevel();

            gui.drawString(10, 20 + (i + 1) * yOffset, skillName, "#FFFFFF", "#000000");
            gui.drawString(10 + skillName.length() + 1, 20 + (i + 1) * yOffset, skillLevel, "#FFFFFF", "#000000");
        }

        gui.refresh();
    }

    @Override
    public void update() {

    }

    public int getPreviousSelected() {
        return previousSelected;
    }

    public void setPreviousSelected(int previousSelected) {
        this.previousSelected = previousSelected;
    }

    public void drawSkillsLabels(Position startingPos, GUI gui, SkillTree model) {
        for(int i = 0; i < model.getSkills().size(); i++) {
            Skill currSkill = model.getSkills().get(i);
            String skillLabel = currSkill.getName();

            int textXPos = 0;
            int textYPos = 0;
            switch (i) {
                case 0:
                    textXPos = startingPos.getX() + SkillTreeViewerConstants.LEFT_OFFSET + (SkillTreeViewerConstants.SKILL_CONTAINER_WIDTH - skillLabel.length()) / 2;
                    textYPos = startingPos.getY() + SkillTreeViewerConstants.TOP_OFFSET + SkillTreeViewerConstants.SKILL_CONTAINER_HEIGHT / 2;
                    break;
                case 1:
                    textXPos = startingPos.getX() + this.image.getDimensions().getWidth() - SkillTreeViewerConstants.RIGHT_OFFSET - SkillTreeViewerConstants.SKILL_CONTAINER_WIDTH + skillLabel.length() / 2;
                    textYPos = startingPos.getY() + SkillTreeViewerConstants.TOP_OFFSET + SkillTreeViewerConstants.SKILL_CONTAINER_HEIGHT / 2;
                    break;
                case 2:
                    textXPos = startingPos.getX() + SkillTreeViewerConstants.LEFT_OFFSET + (SkillTreeViewerConstants.SKILL_CONTAINER_WIDTH - skillLabel.length()) / 2;
                    textYPos = startingPos.getY() + this.image.getDimensions().getHeight() - SkillTreeViewerConstants.BOTTOM_OFFSET - SkillTreeViewerConstants.SKILL_CONTAINER_HEIGHT / 2 - 2;
                    break;
                case 3:
                    textXPos = startingPos.getX() + this.image.getDimensions().getWidth() - SkillTreeViewerConstants.RIGHT_OFFSET - SkillTreeViewerConstants.SKILL_CONTAINER_WIDTH + skillLabel.length() / 2;
                    textYPos = startingPos.getY() + this.image.getDimensions().getHeight() - SkillTreeViewerConstants.BOTTOM_OFFSET - SkillTreeViewerConstants.SKILL_CONTAINER_HEIGHT / 2 - 2;
                    break;
                default:
                    break;
            }
            String charColor = "#FFFFFF";
            if(model.getSelected() == i) charColor = "#00FF00";
            
            gui.drawString(textXPos, textYPos, skillLabel, charColor, "#000000");
        }
    }
}
