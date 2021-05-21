package Terrarius.Model.Game.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ToolbarTest {
    private Toolbar toolbar;
    private Item item;

    @BeforeEach
    public void setup() {
        toolbar = new Toolbar();
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
