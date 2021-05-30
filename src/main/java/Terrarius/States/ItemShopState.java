package Terrarius.States;

import Terrarius.Controller.Controller;
import Terrarius.Controller.ItemShop.ItemShopController;
import Terrarius.Model.ItemShop.ItemShop;
import Terrarius.Viewer.ItemShop.ItemShopViewer;
import Terrarius.Viewer.Viewer;

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
