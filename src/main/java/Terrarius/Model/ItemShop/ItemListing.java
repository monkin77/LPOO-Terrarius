package Terrarius.Model.ItemShop;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.buffs.*;
import Terrarius.Model.Game.items.tools.Axe;
import Terrarius.Model.Game.items.tools.Pickaxe;
import Terrarius.Model.Game.items.tools.Shovel;
import Terrarius.Model.Game.items.tools.Sword;

public class ItemListing {
    private String item;
    private int price;

    public ItemListing(String item, int price) {
        this.item = item;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String  getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Item generateNew(Hero hero){
        switch (item){
            case "Apple": return new Apple(hero);
            case "Banana": return new Banana(hero);
            case "BattlePotion": return new BattlePotion(hero);
            case "ElasticPotion": return new ElasticPotion(hero);
            case "SwiftnessPotion": return new SwiftnessPotion(hero);
            case "Axe": return new Axe(hero);
            case "Pickaxe": return new Pickaxe(hero);
            case "Shovel": return new Shovel(hero);
            case "Sword": return new Sword(hero);
        }
        return null;
    }

}
