package Terrarius.Controller.SkillTree;

import Terrarius.Controller.Controller;
import Terrarius.GUI.GUI;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.SkillTree.SkillTree;
import Terrarius.Model.SkillTree.Skills.Skill;
import Terrarius.States.SkillTreeState;
import Terrarius.Terrarius;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SkillTreeController extends Controller<SkillTree> {
    private List<GUI.ACTION> actions;

    public SkillTreeController(SkillTree model) {
        super(model);
        this.actions = new ArrayList<>();
    }

    @Override
    public void giveActions(Terrarius terrarius, GUI gui) throws IOException {
        // Need to append since getNextActions is called more times than the state.update()
        this.actions.addAll(gui.getNextActions());
    }

    @Override
    public void update(Terrarius terrarius) throws FileNotFoundException, URISyntaxException {
        for(GUI.ACTION action : this.actions) {
            switch (action) {
                case SKILL_TREE:
                    this.actions.clear();
                    ((SkillTreeState) terrarius.getSkillTreeState()).resetSkillTreeViewer();
                    terrarius.setState(terrarius.getGameState());
                    return;
                case ST_LEFT:
                    getModel().previousSkill();
                    break;
                case ST_RIGHT:
                    getModel().nextSkill();
                    break;
                case SELECT:
                    this.upgradeSkill(getModel().getSelected());
                    break;
            }
        }

        this.actions.clear();
    }

    public void upgradeSkill(int selectedSkill) {
        Skill selSkill = this.getModel().getSkills().get(selectedSkill);
        this.getModel().upgradeSkill(selSkill);
    }

    public List<GUI.ACTION> getActions() {
        return actions;
    }

    public void setActions(List<GUI.ACTION> actions) {
        this.actions = actions;
    }
}
