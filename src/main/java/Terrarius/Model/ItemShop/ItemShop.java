package Terrarius.Model.ItemShop;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.MenuTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemShop extends MenuTemplate<ItemListing> {
    private int usedPoints = 0;
    private int selectedSlot = 1;
    private final Hero hero;

    public ItemShop(Hero hero) {
        super();
        this.hero = hero;
    }

    public Hero getHero() {
        return hero;
    }

    public String getItemName(int index){
        return this.getOption(index).getItem();
    }

    public int getItemPrice(int index){
        return this.getOption(index).getPrice();
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
        ItemListing itemListing = getSelectedOption();
        if (availablePoints < itemListing.getPrice()) return;
        this.usedPoints += itemListing.getPrice();

        try {
            this.hero.getToolBar().setItem(selectedSlot, itemListing.generateNew(this.hero));
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<ItemListing> initOptions() {
       List<ItemListing> itemListingList = new ArrayList<>();

       try {
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
       } catch (FileNotFoundException | URISyntaxException e) {
           e.printStackTrace();
       }

       return itemListingList;
    }
}
