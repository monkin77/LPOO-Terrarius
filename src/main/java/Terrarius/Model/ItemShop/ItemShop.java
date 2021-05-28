package Terrarius.Model.ItemShop;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.tools.Tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ItemShop {

    private int usedPoints = 0;
    private int selectedItem = 0;
    private int selectedSlot = 1;

    private final List<ItemListing> itemListingList = new ArrayList<>();

    private final Hero hero;

    public ItemShop(Hero hero) throws FileNotFoundException, URISyntaxException {
        this.hero = hero;
        loadItems();
    }

    public Hero getHero() {
        return hero;
    }

    public int getSelectedItem() {
        return selectedItem;
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
        if (itemListing == null || availablePoints < itemListing.getPrice()) return;
        this.usedPoints += itemListing.getPrice();

        try {
            this.hero.getToolBar().setItem(selectedSlot, itemListing.generateNew(this.hero));
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadItems() throws URISyntaxException, FileNotFoundException {
        URL resource = ItemShop.class.getResource("/assets/tools/Shop.txt");
        Scanner scanner = new Scanner(new File(resource.toURI()));

        String itemName;
        int itemPrice;
        while (scanner.hasNext()) {
            itemName = scanner.next();
            itemPrice = scanner.nextInt();
            itemListingList.add(new ItemListing(itemName, itemPrice, ItemListing.ITEM_TYPE.TOOL));
        }

        resource = ItemShop.class.getResource("/assets/buffs/Shop.txt");
        scanner = new Scanner(new File(resource.toURI()));

        while (scanner.hasNext()) {
            itemName = scanner.next();
            itemPrice = scanner.nextInt();
            itemListingList.add(new ItemListing(itemName, itemPrice, ItemListing.ITEM_TYPE.BUFF));
        }
    }
}
