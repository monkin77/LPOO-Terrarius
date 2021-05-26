package Terrarius.Model.Game.items;

import Terrarius.Model.Game.elements.hero.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ToolbarTest {
    private Toolbar toolbar;
    private Item item;
    private Hero hero;

    @BeforeEach
    public void setup() {
        hero = Mockito.mock(Hero.class);
        toolbar = new Toolbar(hero);
        item = Mockito.mock(Item.class);
    }

    @Test
    public void setItem() {
        Assertions.assertNull(toolbar.getItem(2));

        toolbar.setItem(2, item);
        Assertions.assertSame(item, toolbar.getItem(2));
    }

    @Test
    public void removeItem() {
        toolbar.setItem(2, item);
        Assertions.assertSame(item, toolbar.getItem(2));

        toolbar.removeItem(2);
        Assertions.assertNull(toolbar.getItem(2));
    }

    @Test
    public void findFreeSlot() {
        Assertions.assertEquals(1, toolbar.findFreeSlot());

        toolbar.setItem(1, item);
        toolbar.setItem(2, item);

        Assertions.assertEquals(3, toolbar.findFreeSlot());
    }

    @Test
    public void noFreeSlots() {
        for (int i = 1; i <= 9; ++i)
            toolbar.setItem(i, item);

        Assertions.assertEquals(-1, toolbar.findFreeSlot());
    }
}
