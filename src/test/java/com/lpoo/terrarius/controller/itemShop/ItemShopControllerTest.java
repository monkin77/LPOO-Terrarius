package com.lpoo.terrarius.controller.itemShop;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.itemShop.ItemShop;
import com.lpoo.terrarius.states.GameState;
import com.lpoo.terrarius.states.TransitionState;
import com.lpoo.terrarius.Terrarius;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemShopControllerTest {
    private ItemShop itemShop;
    private GUI gui;
    private Terrarius game;
    private ItemShopController controller;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);
        itemShop = Mockito.mock(ItemShop.class);
        game = Mockito.mock(Terrarius.class);
        controller = new ItemShopController(itemShop);
    }

    @Test
    public void changeSlots() throws IOException, URISyntaxException {
        List<GUI.ACTION> actionList = Arrays.asList(GUI.ACTION.SLOT1, GUI.ACTION.SLOT2,
                GUI.ACTION.SLOT3, GUI.ACTION.SLOT4, GUI.ACTION.SLOT5, GUI.ACTION.SLOT6, GUI.ACTION.SLOT7,
                GUI.ACTION.SLOT8, GUI.ACTION.SLOT9);

        for (int i = 0; i < actionList.size(); ++i) {
            Mockito.when(gui.getNextActions()).thenReturn(Collections.singletonList(actionList.get(i)));
            controller.giveActions(game, gui);
            controller.update(game);
            Mockito.verify(itemShop, Mockito.times(1)).setSelectedSlot(i + 1);
        }
    }

    @Test
    public void leaveShop() throws IOException, URISyntaxException {
        GameState gameState = Mockito.mock(GameState.class);
        TransitionState trans = Mockito.mock(TransitionState.class);
        Mockito.when(trans.getSavedState()).thenReturn(gameState);
        Mockito.when(game.getState()).thenReturn(trans);

        Mockito.when(gui.getNextActions()).thenReturn(Collections.singletonList(GUI.ACTION.ITEM_SHOP));
        controller.giveActions(game, gui);
        controller.update(game);
        Mockito.verify(game, Mockito.times(1)).setState(gameState);
    }

    @Test
    public void menuOptions() throws IOException, URISyntaxException {
        Mockito.when(gui.getNextActions()).thenReturn(Collections.singletonList(GUI.ACTION.LEFT_MENU));
        controller.giveActions(game, gui);
        controller.update(game);

        Mockito.verify(itemShop, Mockito.times(1)).previousOption();

        Mockito.when(gui.getNextActions()).thenReturn(Collections.singletonList(GUI.ACTION.RIGHT_MENU));
        controller.giveActions(game, gui);
        controller.update(game);

        Mockito.verify(itemShop, Mockito.times(1)).nextOption();

        Mockito.when(gui.getNextActions()).thenReturn(Collections.singletonList(GUI.ACTION.SELECT));
        controller.giveActions(game, gui);
        controller.update(game);

        Mockito.verify(itemShop, Mockito.times(1)).buyItem();
    }
}
