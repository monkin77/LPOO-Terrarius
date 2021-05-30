package com.lpoo.terrarius.states;

import com.lpoo.terrarius.controller.Controller;
import com.lpoo.terrarius.controller.itemShop.ItemShopController;
import com.lpoo.terrarius.model.itemShop.ItemShop;
import com.lpoo.terrarius.viewer.itemShop.ItemShopViewer;
import com.lpoo.terrarius.viewer.Viewer;

public class ItemShopState extends TransitionState<ItemShop> {
    public ItemShopState(ItemShop itemShop, State savedState) {
        super(itemShop, savedState);
    }

    @Override
    protected Viewer<ItemShop> createViewer() {
        return new ItemShopViewer();
    }

    @Override
    protected Controller<ItemShop> createController() {
        return new ItemShopController(getModel());
    }
}
