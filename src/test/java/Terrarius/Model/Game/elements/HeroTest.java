package Terrarius.Model.Game.elements;

/*
import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.Toolbar;
import Terrarius.Model.Game.items.tools.Axe;
import Terrarius.Model.Game.items.tools.Sword;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HeroTest {
    Hero hero;
    Toolbar toolbar;

    @BeforeEach
    public void createHero() {
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
    public void addItemFreeSlot() {
        Mockito.when(toolbar.findFreeSlot()).thenReturn(0);

        Item item = new Sword(hero);
        hero.addItemFreeSlot(item);
        Mockito.verify(toolbar, Mockito.times(1)).setItem(Mockito.anyInt(), Mockito.eq(item));
    }

    @Test
    public void addItemNoSlots() {
        Mockito.when(toolbar.findFreeSlot()).thenReturn(-1);

        hero.addItemFreeSlot(new Sword(hero));
        Mockito.verify(toolbar, Mockito.never()).setItem(Mockito.anyInt(), Mockito.any());
    }

    @Test
    public void addItemAvailableSlot() {
        Item item = new Sword(hero);
        hero.addItem(1, item);
        Mockito.verify(toolbar, Mockito.times(1)).setItem(1, item);
    }

    @Test
    public void addItemTakenSlot() {
        Item axe = new Axe(hero);
        hero.addItem(1, axe);

        Item sword = new Sword(hero);
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
}*/
