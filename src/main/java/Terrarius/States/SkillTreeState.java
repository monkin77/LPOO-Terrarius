package Terrarius.States;

import Terrarius.Controller.Controller;
import Terrarius.Controller.SkillTree.SkillTreeController;
import Terrarius.Model.SkillTree.SkillTree;
import Terrarius.Viewer.SkillTree.SkillTreeViewer;
import Terrarius.Viewer.Viewer;

public class SkillTreeState extends State<SkillTree> {
    public SkillTreeState(SkillTree skillTree) {
        super(skillTree);
    }

    @Override
    protected Viewer<SkillTree> getViewer() {
        return new SkillTreeViewer();
    }

    @Override
    protected Controller<SkillTree> getController() {
        return new SkillTreeController();
    }


}
