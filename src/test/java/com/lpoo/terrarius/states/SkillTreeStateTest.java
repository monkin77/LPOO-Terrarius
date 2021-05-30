package com.lpoo.terrarius.states;

import com.lpoo.terrarius.controller.skillTree.SkillTreeController;
import com.lpoo.terrarius.model.skillTree.SkillTree;
import com.lpoo.terrarius.viewer.skillTree.SkillTreeViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SkillTreeStateTest {
    private SkillTreeState state;
    private State savedState;
    private SkillTree skillTree;

    @BeforeEach
    public void setup() {
        this.skillTree = Mockito.mock(SkillTree.class);
        this.savedState = Mockito.mock(GameState.class);

        this.state = new SkillTreeState(this.skillTree, savedState);
    }

    @Test
    public void skillTreeSpecifiers() {
        Assertions.assertTrue(state.getModel() instanceof SkillTree);
        Assertions.assertTrue(state.getController() instanceof SkillTreeController);
        Assertions.assertTrue(state.getViewer() instanceof SkillTreeViewer);
        Assertions.assertTrue(state.getSavedState() instanceof GameState);
    }
}