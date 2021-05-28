package Terrarius.Viewer.ItemShop;

import Terrarius.GUI.GUI;
import Terrarius.Model.ItemShop.ItemShop;
import Terrarius.Viewer.Viewer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ItemShopViewer extends Viewer<ItemShop> {
    @Override
    public void update() {

    }

    @Override
    public void draw(GUI gui, ItemShop model) throws IOException {

        gui.clear();

        String availablePoints = "Available points: " + model.getCurrentPoints();
        String selectedItem = "Selected item: " + model.getItemName(model.getSelectedItem()) + " Price: " + model.getItemPrice(model.getSelectedItem());
        String selectedSlot = "Selected slot: " + model.getSelectedSlot();

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

        try { //TODO for the flickering
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
