package Terrarius.Model.Game.elements;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.Toolbar;
import Terrarius.Model.Game.items.tools.Tool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class HeroTest {
    Hero hero;
    Toolbar toolbar;

    @BeforeEach
    public void createHero() throws FileNotFoundException, URISyntaxException {
        hero = new Hero(new Position(10, 10));
        toolbar = Mockito.mock(Toolbar.class);
        hero.setToolBar(toolbar);
    }

    @Test
    public void startLevel() {
        Level heroLevel = hero.getStats().getLevel();
        Assertions.assertEquals(heroLevel.getNumLevel(), 1);
        Assertions.assertEquals(heroLevel.getCurrentXP(), 0);
    }

    @Test
    public void startHealth() {
        Assertions.assertEquals(100, hero.getStats().getHp());
    }

    @Test
    public void addItemFreeSlot() throws FileNotFoundException, URISyntaxException {
        Mockito.when(toolbar.findFreeSlot()).thenReturn(0);

        Item item = new Tool(hero, "Sword");
        hero.addItemFreeSlot(item);
        Mockito.verify(toolbar, Mockito.times(1)).setItem(Mockito.anyInt(), Mockito.eq(item));
    }

    @Test
    public void addItemNoSlots() throws FileNotFoundException, URISyntaxException {
        Mockito.when(toolbar.findFreeSlot()).thenReturn(-1);

        hero.addItemFreeSlot(new Tool(hero, "Sword"));
        Mockito.verify(toolbar, Mockito.never()).setItem(Mockito.anyInt(), Mockito.any());
    }

    @Test
    public void addItemAvailableSlot() throws FileNotFoundException, URISyntaxException {
        Item item = new Tool(hero, "Sword");
        hero.addItem(1, item);
        Mockito.verify(toolbar, Mockito.times(1)).setItem(1, item);
    }

    @Test
    public void addItemTakenSlot() throws FileNotFoundException, URISyntaxException {
        Item axe = new Tool(hero, "Axe");
        hero.addItem(1, axe);

        Item sword = new Tool(hero, "Sword");
        hero.addItem(1, sword);

        // Item is replaced
        Mockito.verify(toolbar, Mockito.times(1)).setItem(1, axe);
        Mockito.verify(toolbar, Mockito.times(1)).setItem(1, sword);
    }

    @Test
    public void removeItem() {
        hero.removeItem(1);
        Mockito.verify(toolbar, Mockito.times(1)).removeItem(1);
    }

    @Test
    public void dimensionsWithItem() throws FileNotFoundException, URISyntaxException {
        Tool tool = new Tool(hero, "Pickaxe");

        Mockito.when(toolbar.getActiveItemIdx()).thenReturn(1);
        Mockito.when(toolbar.getActiveItem()).thenReturn(tool);

        System.out.println(hero.getDimensionsWithItem().getWidth());
        Assertions.assertEquals(hero.getDimensionsWithItem().getWidth(),
                hero.getDimensions().getWidth()+tool.getDimensions().getWidth());
    }
}
