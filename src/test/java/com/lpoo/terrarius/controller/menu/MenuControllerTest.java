package com.lpoo.terrarius.controller.menu;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.menu.Menu;
import com.lpoo.terrarius.states.GameState;
import com.lpoo.terrarius.Terrarius;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MenuControllerTest {
    private Menu menu;
    private MenuController controller;
    private GUI gui;
    private Terrarius game;
    private List<GUI.ACTION> actions;

    @BeforeEach
    public void setup() throws IOException {
        actions = new ArrayList<>();

        menu = Mockito.mock(Menu.class);
        gui = Mockito.mock(GUI.class);
        Mockito.when(gui.getNextActions()).thenReturn(actions);
        game = Mockito.mock(Terrarius.class);
        controller = new MenuController(menu);
    }

    @Test
    public void menuActions() throws IOException, URISyntaxException {
        actions.add(GUI.ACTION.UP_MENU);
        controller.giveActions(game, gui);
        controller.update(game);
        Mockito.verify(menu, Mockito.times(1)).previousOption();

        actions.add(GUI.ACTION.DOWN_MENU);
        controller.giveActions(game, gui);
        controller.update(game);
        Mockito.verify(menu, Mockito.times(1)).nextOption();
    }

    @Test
    public void selectAction() throws IOException, URISyntaxException {
        GameState gameState = Mockito.mock(GameState.class);
        Mockito.when(game.createGameState()).thenReturn(gameState);

        Mockito.when(menu.isPlaySelected()).thenReturn(true);
        actions.add(GUI.ACTION.SELECT);

        controller.giveActions(game, gui);
        controller.update(game);

        Mockito.verify(game, Mockito.times(1)).setState(gameState);

        Mockito.when(menu.isPlaySelected()).thenReturn(false);
        Mockito.when(menu.isQuitSelected()).thenReturn(true);
        actions.add(GUI.ACTION.SELECT);

        controller.giveActions(game, gui);
        controller.update(game);

        Mockito.verify(game, Mockito.times(1)).setState(null);
    }
}
