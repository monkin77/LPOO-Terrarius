package com.lpoo.terrarius.states;

import com.lpoo.terrarius.controller.itemShop.ItemShopController;
import com.lpoo.terrarius.model.itemShop.ItemShop;
import com.lpoo.terrarius.viewer.itemShop.ItemShopViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ItemShopStateTest {
    private ItemShop itemShop;
    private ItemShopState state;
    private State savedState;

    @BeforeEach
    public void setup() {
        this.itemShop = Mockito.mock(ItemShop.class);
        this.savedState = Mockito.mock(GameState.class);

        this.state = new ItemShopState(this.itemShop, this.savedState);
    }

    @Test
    public void itemShopSpecifiers() {
        Assertions.assertTrue(state.getModel() instanceof ItemShop);
        Assertions.assertTrue(state.getController() instanceof ItemShopController);
        Assertions.assertTrue(state.getViewer() instanceof ItemShopViewer);
        Assertions.assertTrue(state.getSavedState() instanceof GameState);
    }
}