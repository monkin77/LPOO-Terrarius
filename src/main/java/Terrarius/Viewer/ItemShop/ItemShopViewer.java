package Terrarius.Viewer.ItemShop;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.ItemShop.ItemShop;
import Terrarius.Viewer.Viewer;

import java.io.IOException;

public class ItemShopViewer extends Viewer<ItemShop> {
    private int previousIndex = -1;
    private int previousSlot = -1;

    @Override
    public void draw(GUI gui, ItemShop model) throws IOException {
        if (model.getSelectedIndex() == previousIndex
            && model.getSelectedSlot() == previousSlot) return;

        previousIndex = model.getSelectedIndex();
        previousSlot = model.getSelectedSlot();

        gui.clear();

        String availablePoints = "Available points: " + model.getCurrentPoints();
        String selectedItem = "Selected item: " + model.getItemName(model.getSelectedIndex()) + " Price: " + model.getItemPrice(model.getSelectedIndex());
        Item activeItem = model.getHero().getToolBar().getItem(model.getSelectedSlot());
        String selectedSlot = "Selected slot: " + model.getSelectedSlot() + " Current Item: " + (activeItem == null ? "Empty" : activeItem.getComponentName());

        gui.drawString(1,1,
                availablePoints,
                "#FFFFFF",
                "#000000");

        gui.drawString(1,3,
                selectedItem,
                "#FFFFFF",
                "#000000");

        gui.drawString(1,5,
                selectedSlot,
                "#FFFFFF",
                "#000000");

        gui.refresh();
    }
}
