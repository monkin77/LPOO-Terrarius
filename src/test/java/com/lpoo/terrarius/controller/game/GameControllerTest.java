
package com.lpoo.terrarius.controller.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.Terrarius;
import com.lpoo.terrarius.model.game.arena.Arena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameControllerTest {
    private GameController gameController;
    private ArenaController arenaController;
    private Terrarius terrarius;
    private Arena arena;
    private GUI gui;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);
        Mockito.when(gui.getMouseX()).thenReturn(10);
        Mockito.when(gui.getMouseY()).thenReturn(20);
        Mockito.when(gui.getFontSize()).thenReturn(10);

        arenaController = Mockito.mock(ArenaController.class);
        arena = Mockito.mock(Arena.class);
        terrarius = Mockito.mock(Terrarius.class);
        gameController = new GameController(arena, arenaController);
    }

    @Test
    public void giveActions() throws IOException {

        List<GUI.ACTION> actions= Arrays.asList(GUI.ACTION.RIGHT, GUI.ACTION.UP, GUI.ACTION.LEFT);
        Mockito.when(gui.getNextActions()).thenReturn(actions);

        gameController.giveActions(terrarius, gui);
        Mockito.verify(arenaController, Mockito.times(1)).addActions(actions);
        Mockito.verify(arenaController, Mockito.times(1)).setHeroTargetPosition(Mockito.any());
    }

    @Test
    public void quitAction() throws IOException {
        List<GUI.ACTION> actions = Collections.singletonList(GUI.ACTION.QUIT);
        Mockito.when(gui.getNextActions()).thenReturn(actions);

        gameController.giveActions(terrarius, gui);
        Mockito.verify(arenaController, Mockito.never()).addActions(actions);
        Mockito.verify(terrarius, Mockito.times(1)).createMenuState();
    }

    @Test
    public void skillTreeAction() throws IOException {
        List<GUI.ACTION> actions = Collections.singletonList(GUI.ACTION.SKILL_TREE);
        Mockito.when(gui.getNextActions()).thenReturn(actions);

        gameController.giveActions(terrarius, gui);
        Mockito.verify(arenaController, Mockito.never()).addActions(actions);
        Mockito.verify(terrarius, Mockito.times(1)).createSkillTreeState();
    }

    @Test
    public void itemShopAction() throws IOException {
        List<GUI.ACTION> actions = Collections.singletonList(GUI.ACTION.ITEM_SHOP);
        Mockito.when(gui.getNextActions()).thenReturn(actions);

        gameController.giveActions(terrarius, gui);
        Mockito.verify(arenaController, Mockito.never()).addActions(actions);
        Mockito.verify(terrarius, Mockito.times(1)).createItemShopState();
    }

    @Test
    public void update() {
        Mockito.when(arenaController.checkEnd()).thenReturn(false);

        gameController.update(terrarius);
        Mockito.verify(arenaController, Mockito.times(1)).update();
        Mockito.verify(terrarius, Mockito.never()).setState(Mockito.any());

        Mockito.when(arenaController.checkEnd()).thenReturn(true);

        gameController.update(terrarius);
        Mockito.verify(arenaController, Mockito.times(2)).update();
        Mockito.verify(terrarius, Mockito.times(1)).setState(Mockito.any());
    }
}
