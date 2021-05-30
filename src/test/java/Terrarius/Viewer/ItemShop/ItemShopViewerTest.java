package Terrarius.Viewer.ItemShop;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.Toolbar;
import Terrarius.Model.ItemShop.ItemShop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ItemShopViewerTest {
    private ItemShop itemShop;
    private GUI gui;
    private ItemShopViewer viewer;
    private Hero hero;
    private Toolbar toolbar;
    private Item item;

    @BeforeEach
    public void setup() {
        itemShop = Mockito.mock(ItemShop.class);
        gui = Mockito.mock(GUI.class);
        viewer = new ItemShopViewer();

        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getStats()).thenReturn(new HeroStats(new Level(1, 0), 100, 2, 1, 5));

        item = Mockito.mock(Item.class);

        toolbar = Mockito.mock(Toolbar.class);
        Mockito.when(toolbar.getItem(Mockito.anyInt())).thenReturn(item);
        Mockito.when(hero.getToolBar()).thenReturn(toolbar);

        Mockito.when(itemShop.getHero()).thenReturn(hero);
        Mockito.when(itemShop.getItemName(Mockito.anyInt())).thenReturn("Item");
        Mockito.when(itemShop.getItemPrice(Mockito.anyInt())).thenReturn(5);
        Mockito.when(itemShop.getSelectedIndex()).thenReturn(0);
        Mockito.when(itemShop.getSelectedSlot()).thenReturn(1);
    }

    @Test
    public void draw() throws IOException {
        viewer.draw(gui, itemShop);

        //TODO NEED NEW PR
    }
}
