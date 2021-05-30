package com.lpoo.terrarius.model.itemShop;

import com.lpoo.terrarius.model.game.Level;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ItemShopTest {

    ItemShop itemShop;
    Hero hero;


    @BeforeEach
    public void setUp() throws FileNotFoundException, URISyntaxException {

        hero = new Hero(new Position(0, 0));
        itemShop = new ItemShop(hero);

    }

    @Test
    public void attemptToBuyWithMoney(){

        hero.setLevel(new Level(10, 10));

        itemShop.setSelectedSlot(5);
        itemShop.nextOption();
        itemShop.nextOption();
        itemShop.buyItem();
        Assertions.assertEquals("Sword",
                hero.getToolBar().getItem(itemShop.getSelectedSlot()).getComponentName());
        Assertions.assertEquals(itemShop.getHero().getStats().getCurrentPoints(), 10*10-itemShop.getItemPrice(itemShop.getSelectedIndex()));
    }

    @Test
    public void attemptToBuyWithoutMoney(){

        hero.setLevel(new Level(1, 10));

        itemShop.setSelectedSlot(5);
        itemShop.nextOption();
        itemShop.nextOption();
        itemShop.buyItem();
        Assertions.assertNull(hero.getToolBar().getItem(itemShop.getSelectedSlot()));
        Assertions.assertEquals(itemShop.getHero().getStats().getCurrentPoints(), 10);

    }

}
