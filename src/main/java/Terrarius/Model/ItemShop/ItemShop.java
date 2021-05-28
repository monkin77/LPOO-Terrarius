package Terrarius.Model.ItemShop;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemShop {

    private int usedPoints = 0;
    private int selectedItem = 0;
    private int selectedSlot = 1;

    private final List<ItemListing> itemListingList = new ArrayList<>();

    private final Hero hero;

    public ItemShop(Hero hero){
        this.hero = hero;
        this.itemListingList.add(new ItemListing("Apple", 10));
        this.itemListingList.add(new ItemListing("Banana", 10));
        this.itemListingList.add(new ItemListing("BattlePotion", 15));
        this.itemListingList.add(new ItemListing("ElasticPotion", 15));
        this.itemListingList.add(new ItemListing("SwiftnessPotion", 15));
        this.itemListingList.add(new ItemListing("Shovel", 10));
        this.itemListingList.add(new ItemListing("Axe", 20));
        this.itemListingList.add(new ItemListing("Sword", 25));
        this.itemListingList.add(new ItemListing("Pickaxe", 30));
    }

    public Hero getHero() {
        return hero;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void nextItem(){
        this.selectedItem = (selectedItem + 1) % this.itemListingList.size();
    }

    public String getItemName(int index){
        return this.itemListingList.get(index).getItem();
    }

    public int getItemPrice(int index){
        return this.itemListingList.get(index).getPrice();
    }

    public void previousItem(){
        int nextSel = this.selectedItem - 1;
        while(nextSel < 0) nextSel += itemListingList.size();
        this.selectedItem = nextSel % itemListingList.size();
    }

    public void setSelectedSlot(int selectedSlot){
        this.selectedSlot = selectedSlot;
    }

    public int getCurrentPoints(){
        return this.hero.getStats().getCurrentLevel() * 10 - this.usedPoints;
    }

    public int getSelectedSlot(){
        return this.selectedSlot;
    }

    public void buyItem(){
        int availablePoints = this.getCurrentPoints();
        ItemListing itemListing = itemListingList.get(selectedItem);
        if (availablePoints < itemListing.getPrice() || itemListing == null) return;
        this.usedPoints += itemListing.getPrice();
        this.hero.getToolBar().setItem(selectedSlot, itemListing.generateNew(this.hero));
    }
}
