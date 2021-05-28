package Terrarius.Viewer.SkillTree;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.SkillTree.SkillTree;
import Terrarius.Model.SkillTree.Skills.Skill;
import Terrarius.Viewer.Game.GameViewerConstants;
import Terrarius.Viewer.Image.ColoredImage;
import Terrarius.Viewer.Viewer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class SkillTreeViewer extends Viewer<SkillTree> {
    private ColoredImage image;
    int previousSelected;
    int previousNumUpgrades;

    public SkillTreeViewer() {
        super();
        image = new ColoredImage();
        try {
            image.load("Images/SkillTree/SkillTree.txt");
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
            image = null;
        }
        previousSelected = -1;
        previousNumUpgrades = -1;
    }

    @Override
    public void draw(GUI gui, SkillTree model) throws IOException {
        if (!this.needsUpdate(model)) return;
        previousSelected = model.getSelected();
        previousNumUpgrades = model.getUsedPoints();

        gui.clear();

        String title = "Skill Tree";
        gui.drawString((GameViewerConstants.SCREEN_WIDTH - title.length()) /2, 5, title, "#FFFFFF", "#000000");

        Position treePos = new Position(50, 10);
        this.image.draw(treePos, Element.Orientation.RIGHT, gui);

        this.drawSkillsLabels(treePos, gui, model);

        int yOffset = 5;
        String availablePointsString = "Available Points: " + model.getAvailablePoints();
        gui.drawString(10, 20, availablePointsString, "#FFFFFF", "#000000");

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

    /**
     * This function checks if the viewer should update
     * @param model
     * @return true if it needs to update. False otherwise.
     */
    public boolean needsUpdate(SkillTree model) {
        return ( previousSelected != model.getSelected() || previousNumUpgrades != model.getUsedPoints() );
    }

    public int getPreviousSelected() {
        return previousSelected;
    }

    public void setPreviousSelected(int previousSelected) {
        this.previousSelected = previousSelected;
    }

    public void drawSkillsLabels(Position startingPos, GUI gui, SkillTree model) {
        String skillLabel = "Skills";
        int textXPos = startingPos.getX() + SkillTreeViewerConstants.CENTER_CONTAINER_X - skillLabel.length() / 2;
        int textYPos = startingPos.getY() + SkillTreeViewerConstants.CENTER_CONTAINER_Y - 1;
        gui.drawString(textXPos, textYPos, skillLabel, "#FFFFFF", "#000000");

        for(int i = 0; i < model.getSkills().size(); i++) {
            Skill currSkill = model.getSkills().get(i);
            skillLabel = currSkill.getName();

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
