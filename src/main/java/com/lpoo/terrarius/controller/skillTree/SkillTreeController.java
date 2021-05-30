package com.lpoo.terrarius.controller.skillTree;

import com.lpoo.terrarius.controller.Controller;
import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.skillTree.SkillTree;
import com.lpoo.terrarius.model.skillTree.skills.Skill;
import com.lpoo.terrarius.states.TransitionState;
import com.lpoo.terrarius.Terrarius;

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
        this.actions = gui.getNextActions();
    }

    @Override
    public void update(Terrarius terrarius) throws FileNotFoundException, URISyntaxException {
        for(GUI.ACTION action : this.actions) {
            switch (action) {
                case SKILL_TREE:
                    terrarius.setState( ( (TransitionState) terrarius.getState() ).getSavedState() );
                    return;
                case LEFT_MENU:
                    getModel().previousOption();
                    break;
                case RIGHT_MENU:
                    getModel().nextOption();
                    break;
                case SELECT:
                    this.upgradeSkill(getModel().getSelectedIndex());
                    break;
            }
        }

        this.actions.clear();
    }

    private void upgradeSkill(int selectedSkill) {
        Skill selSkill = this.getModel().getOption(selectedSkill);
        this.getModel().upgradeSkill(selSkill);
    }
}
