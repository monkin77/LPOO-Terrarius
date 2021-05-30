package Terrarius.Model.ItemShop;

import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.Toolbar;
import Terrarius.Model.Game.items.buffs.Buff;
import Terrarius.Model.Game.items.tools.Tool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
