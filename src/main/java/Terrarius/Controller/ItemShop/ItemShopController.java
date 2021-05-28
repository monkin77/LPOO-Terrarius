package Terrarius.Controller.ItemShop;

import Terrarius.Controller.Controller;
import Terrarius.GUI.GUI;
import Terrarius.Model.ItemShop.ItemShop;
import Terrarius.States.ItemShopState;
import Terrarius.Terrarius;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ItemShopController extends Controller<ItemShop> {

    private List<GUI.ACTION> actions;

    public ItemShopController(ItemShop model) {
        super(model);
        this.actions = new ArrayList<>();
    }

    @Override
    public void giveActions(Terrarius terrarius, GUI gui) throws IOException {
        this.actions = gui.getNextActions();
    }

    @Override
    public void update(Terrarius terrarius) throws FileNotFoundException, URISyntaxException {
        for(GUI.ACTION action : this.actions) {
            switch (action) {
                case ITEM_SHOP:
                    terrarius.setState(terrarius.getGameState());
                    return;
                case LEFT_MENU:
                    getModel().previousOption();
                    break;
                case RIGHT_MENU:
                    getModel().nextOption();
                    break;
                case SELECT:
                    getModel().buyItem();
                    break;
                case SLOT1:
                    getModel().setSelectedSlot(1);
                    break;
                case SLOT2:
                    getModel().setSelectedSlot(2);
                    break;
                case SLOT3:
                    getModel().setSelectedSlot(3);
                    break;
                case SLOT4:
                    getModel().setSelectedSlot(4);
                    break;
                case SLOT5:
                    getModel().setSelectedSlot(5);
                    break;
                case SLOT6:
                    getModel().setSelectedSlot(6);
                    break;
                case SLOT7:
                    getModel().setSelectedSlot(7);
                    break;
                case SLOT8:
                    getModel().setSelectedSlot(8);
                    break;
                case SLOT9:
                    getModel().setSelectedSlot(9);
                    break;
            }
        }
    }
}
