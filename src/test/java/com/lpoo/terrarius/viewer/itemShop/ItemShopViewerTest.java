package com.lpoo.terrarius.viewer.itemShop;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Level;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.model.game.elements.hero.HeroStats;
import com.lpoo.terrarius.model.game.items.Item;
import com.lpoo.terrarius.model.game.items.Toolbar;
import com.lpoo.terrarius.model.itemShop.ItemListing;
import com.lpoo.terrarius.model.itemShop.ItemShop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

public class ItemShopViewerTest {
    private ItemShop itemShop;
    private GUI gui;
    private ItemShopViewer viewer;
    private Hero hero;
    private Toolbar toolbar;
    private Item item;
    private ItemListing itemListing;

    @BeforeEach
    public void setup() {
        itemShop = Mockito.mock(ItemShop.class);
        gui = Mockito.mock(GUI.class);
        viewer = new ItemShopViewer();

        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getStats()).thenReturn(new HeroStats(new Level(1, 0), 100, 2, 1, 5));

        item = Mockito.mock(Item.class);
        Mockito.when(item.getComponentName()).thenReturn("Apple");

        itemListing = Mockito.mock(ItemListing.class);
        Mockito.when(itemListing.getItem()).thenReturn("Apple");

        toolbar = Mockito.mock(Toolbar.class);
        Mockito.when(toolbar.getItem(Mockito.anyInt())).thenReturn(item);
        Mockito.when(hero.getToolBar()).thenReturn(toolbar);

        Mockito.when(itemShop.getHero()).thenReturn(hero);
        Mockito.when(itemShop.getItemName(Mockito.anyInt())).thenReturn("Apple");
        Mockito.when(itemShop.getItemPrice(Mockito.anyInt())).thenReturn(5);
        Mockito.when(itemShop.getSelectedIndex()).thenReturn(0);
        Mockito.when(itemShop.getSelectedSlot()).thenReturn(1);
        Mockito.when(itemShop.getOption(Mockito.anyInt())).thenReturn(itemListing);
        Mockito.when(itemShop.getOptions()).thenReturn(Collections.singletonList(itemListing));
    }

    @Test
    public void draw() throws IOException, URISyntaxException {
        viewer.draw(gui, itemShop);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();

        Mockito.verify(gui, Mockito.times(13)).drawString(Mockito.anyInt(), Mockito.anyInt(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        Mockito.verify(gui, Mockito.times(384)).drawCharacter(Mockito.anyInt(), Mockito.anyInt(),
                Mockito.anyChar());
    }
}
