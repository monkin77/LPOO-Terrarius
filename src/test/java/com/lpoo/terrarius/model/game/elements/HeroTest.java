package com.lpoo.terrarius.model.game.elements;

import com.lpoo.terrarius.model.game.Level;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.model.game.items.Item;
import com.lpoo.terrarius.model.game.items.Toolbar;
import com.lpoo.terrarius.model.game.items.buffs.Buff;
import com.lpoo.terrarius.model.game.items.buffs.BuffStats;
import com.lpoo.terrarius.model.game.items.tools.Tool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @Test
    public void targetPosition(){
        this.hero.setPosition(new Position(0, 0));
        this.hero.setRange(20.0);

        this.hero.setTargetPosition(new Position(0, 0));
        Assertions.assertTrue(this.hero.targetWithinRange());

        this.hero.setTargetPosition(new Position(20, 0));
        Assertions.assertTrue(this.hero.targetWithinRange());

        this.hero.setTargetPosition(new Position(20 + this.hero.getDimensions().getWidth()/2, 0));
        Assertions.assertFalse(this.hero.targetWithinRange());

        this.hero.setTargetPosition(new Position(20 + this.hero.getDimensions().getWidth()/2 - 1, 0));
        Assertions.assertTrue(this.hero.targetWithinRange());

        this.hero.setTargetPosition(new Position(
                16 + this.hero.getDimensions().getWidth()/2,
                12 + this.hero.getDimensions().getHeight()/2));
        Assertions.assertTrue(this.hero.targetWithinRange());

        this.hero.setTargetPosition(new Position(
                16 + this.hero.getDimensions().getWidth()/2,
                13 + this.hero.getDimensions().getHeight()/2));
        Assertions.assertFalse(this.hero.targetWithinRange());
    }

    @Test
    public void addBuffs(){

        Buff buffShort = Mockito.mock(Buff.class);
        BuffStats buffStatsShort = Mockito.mock(BuffStats.class);
        Buff buffShort2 = Mockito.mock(Buff.class);
        BuffStats buffStatsShort2 = Mockito.mock(BuffStats.class);
        Buff buffMed = Mockito.mock(Buff.class);
        BuffStats buffStatsMed = Mockito.mock(BuffStats.class);
        Buff buffLong = Mockito.mock(Buff.class);
        BuffStats buffStatsLong = Mockito.mock(BuffStats.class);

        Mockito.when(buffShort.getStats()).thenReturn(buffStatsShort);
        Mockito.when(buffStatsShort.getDuration()).thenReturn(10);
        Mockito.when(buffShort2.getStats()).thenReturn(buffStatsShort2);
        Mockito.when(buffStatsShort2.getDuration()).thenReturn(10);
        Mockito.when(buffMed.getStats()).thenReturn(buffStatsMed);
        Mockito.when(buffStatsMed.getDuration()).thenReturn(15);
        Mockito.when(buffLong.getStats()).thenReturn(buffStatsLong);
        Mockito.when(buffStatsLong.getDuration()).thenReturn(20);

        this.hero.addBuff(buffMed);
        Mockito.verify(buffMed, Mockito.times(0)).getStats();
        Mockito.verify(buffStatsMed, Mockito.times(0)).getDuration();

        this.hero.addBuff(buffShort);
        Mockito.verify(buffMed, Mockito.times(1)).getStats();
        Mockito.verify(buffStatsMed, Mockito.times(1)).getDuration();
        Mockito.verify(buffShort, Mockito.times(1)).getStats();
        Mockito.verify(buffStatsShort, Mockito.times(1)).getDuration();

        this.hero.addBuff(buffShort2);
        Mockito.verify(buffMed, Mockito.times(2)).getStats();
        Mockito.verify(buffStatsMed, Mockito.times(2)).getDuration();
        Mockito.verify(buffShort, Mockito.times(2)).getStats();
        Mockito.verify(buffStatsShort, Mockito.times(2)).getDuration();
        Mockito.verify(buffShort2, Mockito.times(2)).getStats();
        Mockito.verify(buffStatsShort2, Mockito.times(2)).getDuration();

        this.hero.addBuff(buffLong);
        Mockito.verify(buffShort2, Mockito.times(3)).getStats();
        Mockito.verify(buffStatsShort2, Mockito.times(3)).getDuration();
        Mockito.verify(buffMed, Mockito.times(3)).getStats();
        Mockito.verify(buffStatsMed, Mockito.times(3)).getDuration();
        Mockito.verify(buffShort, Mockito.times(3)).getStats();
        Mockito.verify(buffStatsShort, Mockito.times(3)).getDuration();
        Mockito.verify(buffLong, Mockito.times(3)).getStats();
        Mockito.verify(buffStatsLong, Mockito.times(3)).getDuration();


        Assertions.assertEquals(buffShort, this.hero.getActiveBuffs().get(0));
        Assertions.assertEquals(buffShort2, this.hero.getActiveBuffs().get(1));
        Assertions.assertEquals(buffMed, this.hero.getActiveBuffs().get(2));
        Assertions.assertEquals(buffLong, this.hero.getActiveBuffs().get(3));
    }
}
