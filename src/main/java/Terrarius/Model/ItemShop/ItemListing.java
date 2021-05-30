package Terrarius.Model.ItemShop;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.buffs.Buff;
import Terrarius.Model.Game.items.tools.Tool;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ItemListing {
    private String item;
    private final int price;
    private final ITEM_TYPE type;
    enum ITEM_TYPE {TOOL, BUFF}

    public ItemListing(String item, int price, ITEM_TYPE type) {
        this.item = item;
        this.price = price;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public String  getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Item generateNew(Hero hero) throws FileNotFoundException, URISyntaxException {
        switch (type) {
            case TOOL:
                return new Tool(hero, item);
            case BUFF:
                return new Buff(hero, item);
        }
        return null;
    }
}
