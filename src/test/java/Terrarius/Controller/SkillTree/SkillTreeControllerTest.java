package Terrarius.Controller.SkillTree;

import Terrarius.GUI.GUI;
import Terrarius.Model.SkillTree.SkillTree;
import Terrarius.Model.SkillTree.Skills.Skill;
import Terrarius.States.GameState;
import Terrarius.States.TransitionState;
import Terrarius.Terrarius;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SkillTreeControllerTest {
    private SkillTree skillTree;
    private GUI gui;
    private Terrarius game;
    private List<GUI.ACTION> actions;
    private SkillTreeController controller;

    @BeforeEach
    public void setup() throws IOException {
        actions = new ArrayList<>();

        skillTree = Mockito.mock(SkillTree.class);
        gui = Mockito.mock(GUI.class);
        Mockito.when(gui.getNextActions()).thenReturn(actions);
        game = Mockito.mock(Terrarius.class);
        controller = new SkillTreeController(skillTree);
    }

    @Test
    public void upgradeSkill() throws IOException, URISyntaxException {
        Skill skill = Mockito.mock(Skill.class);
        Mockito.when(skillTree.getOption(Mockito.anyInt())).thenReturn(skill);

        actions.add(GUI.ACTION.SELECT);
        controller.giveActions(game, gui);
        controller.update(game);

        Mockito.verify(skillTree, Mockito.times(1)).upgradeSkill(skill);
    }

    @Test
    public void menuActions() throws IOException, URISyntaxException {
        actions.add(GUI.ACTION.LEFT_MENU);
        controller.giveActions(game, gui);
        controller.update(game);
        Mockito.verify(skillTree, Mockito.times(1)).previousOption();

        actions.add(GUI.ACTION.RIGHT_MENU);
        controller.giveActions(game, gui);
        controller.update(game);
        Mockito.verify(skillTree, Mockito.times(1)).nextOption();
    }

    @Test
    public void exitSkillTree() throws IOException, URISyntaxException {
        GameState gameState = Mockito.mock(GameState.class);
        TransitionState trans = Mockito.mock(TransitionState.class);
        Mockito.when(trans.getSavedState()).thenReturn(gameState);
        Mockito.when(game.getState()).thenReturn(trans);

        actions.add(GUI.ACTION.SKILL_TREE);
        controller.giveActions(game, gui);
        controller.update(game);

        Mockito.verify(game, Mockito.times(1)).setState(gameState);
    }
}
