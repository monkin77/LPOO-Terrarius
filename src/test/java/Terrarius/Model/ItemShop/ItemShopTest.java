package Terrarius.Model.ItemShop;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.Game.items.Toolbar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Random;

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
