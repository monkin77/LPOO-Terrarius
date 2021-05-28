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

    private Hero hero;

    public ItemShop(Hero hero){
        this.hero = hero;
        this.itemListingList.add(new ItemListing("Apple", 5));
        this.itemListingList.add(new ItemListing("Banana", 5));
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
