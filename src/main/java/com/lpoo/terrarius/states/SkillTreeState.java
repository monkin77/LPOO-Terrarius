package com.lpoo.terrarius.states;

import com.lpoo.terrarius.controller.Controller;
import com.lpoo.terrarius.controller.skillTree.SkillTreeController;
import com.lpoo.terrarius.model.skillTree.SkillTree;
import com.lpoo.terrarius.viewer.skillTree.SkillTreeViewer;
import com.lpoo.terrarius.viewer.Viewer;

public class SkillTreeState extends TransitionState<SkillTree> {
    public SkillTreeState(SkillTree skillTree, State savedState) {
        super(skillTree, savedState);
    }

    @Override
    protected Viewer<SkillTree> createViewer() {
        return new SkillTreeViewer();
    }

    @Override
    protected Controller<SkillTree> createController() {
        return new SkillTreeController(getModel());
    }
}
