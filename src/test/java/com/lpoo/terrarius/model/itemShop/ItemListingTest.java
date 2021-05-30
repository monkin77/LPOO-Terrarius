package com.lpoo.terrarius.model.itemShop;

import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.model.game.items.Item;
import com.lpoo.terrarius.model.game.items.buffs.Buff;
import com.lpoo.terrarius.model.game.items.tools.Tool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ItemListingTest {

    Hero hero;

    @BeforeEach
    public void setUp() throws FileNotFoundException, URISyntaxException {
        hero = new Hero(new Position(0, 0));
    }

    @Test
    public void createAndGenerateBuffs() throws FileNotFoundException, URISyntaxException {
        Item item = (new ItemListing("Apple", 10, ItemListing.ITEM_TYPE.BUFF)).generateNew(hero);

        Assertions.assertTrue(item instanceof Buff);
        Assertions.assertEquals("Apple", item.getComponentName());
    }

    @Test
    public void createAndGenerateTools() throws FileNotFoundException, URISyntaxException {
        Item item = (new ItemListing("Axe", 10, ItemListing.ITEM_TYPE.TOOL)).generateNew(hero);

        Assertions.assertTrue(item instanceof Tool);
        Assertions.assertEquals("Axe", item.getComponentName());
    }
}
