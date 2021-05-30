package Terrarius.States;

import Terrarius.Controller.Controller;
import Terrarius.Controller.ItemShop.ItemShopController;
import Terrarius.Model.ItemShop.ItemShop;
import Terrarius.Viewer.ItemShop.ItemShopViewer;
import Terrarius.Viewer.Viewer;

public class ItemShopState extends State<ItemShop> {
    public ItemShopState(ItemShop itemShop) {
        super(itemShop);
    }

    @Override
    protected Viewer<ItemShop> createViewer() {
        return new ItemShopViewer();
    }

    @Override
    protected Controller<ItemShop> createController() {
        return new ItemShopController(getModel());
    }

    public void resetItemShopViewer(){
        this.setViewer(createViewer());
    }
}
