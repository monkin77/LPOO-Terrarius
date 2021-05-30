package com.lpoo.terrarius.controller.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Level;
import com.lpoo.terrarius.model.game.elements.Element;
import com.lpoo.terrarius.model.game.elements.hero.HeroStats;
import com.lpoo.terrarius.model.game.items.BlockPlacer;
import com.lpoo.terrarius.model.game.items.Item;
import com.lpoo.terrarius.model.game.items.buffs.Buff;
import com.lpoo.terrarius.model.game.items.Toolbar;
import com.lpoo.terrarius.model.game.items.tools.Tool;
import com.lpoo.terrarius.utils.Dimensions;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.arena.Arena;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HeroControllerTest {
    private HeroController heroController;
    private Hero hero;
    private Arena arena;
    private Toolbar toolbar;
    private Item item;

    @BeforeEach
    public void setup() {
        item = Mockito.mock(Tool.class);
        Mockito.when(item.getDimensions()).thenReturn(new Dimensions(0, 0));

        toolbar = Mockito.mock(Toolbar.class);
        Mockito.when(toolbar.getActiveItem()).thenReturn(item);

        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getToolBar()).thenReturn(toolbar);
        Mockito.when(hero.getDimensions()).thenReturn(new Dimensions(5, 5));
        Mockito.when(hero.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(hero.getStats()).thenReturn(new HeroStats(new Level(5, 0), 100, 5, 1, 10));

        arena = Mockito.mock(Arena.class);

        Mockito.when(arena.getHeight()).thenReturn(100);
        Mockito.when(arena.getWidth()).thenReturn(100);
        Mockito.when(arena.getHero()).thenReturn(hero);

        heroController = new HeroController(arena);
    }

    @Test
    public void moveHeroLeft() {
        heroController.moveHeroLeft();
        Mockito.verify(hero, Mockito.times(1)).setPosition(new Position(9, 10));
        Mockito.verify(hero, Mockito.times(1)).setOrientation(Element.Orientation.LEFT);
    }

    @Test
    public void moveHeroRight() {
        heroController.moveHeroRight();
        Mockito.verify(hero, Mockito.times(1)).setPosition(new Position(11, 10));
        Mockito.verify(hero, Mockito.times(1)).setOrientation(Element.Orientation.RIGHT);
    }

    @Test
    public void moveHeroUp() {
        Mockito.when(arena.hasAdjacentBlock(Mockito.any(), Mockito.any())).thenReturn(true);
        heroController.moveHeroUp();
        Mockito.verify(hero, Mockito.times(1)).setPosition(new Position(10, 9));
    }

    @Test
    public void moveHeroDown() {
        Mockito.when(arena.hasAdjacentBlock(Mockito.any(), Mockito.any())).thenReturn(true);
        heroController.moveHeroDown();
        Mockito.verify(hero, Mockito.times(1)).setPosition(new Position(10, 11));
    }

    @Test
    public void moveHeroOutsideArena() {
        Mockito.when(arena.hasAdjacentBlock(Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.when(arena.getWidth()).thenReturn(10);
        Mockito.when(arena.getHeight()).thenReturn(10);
        Mockito.when(hero.getPosition()).thenReturn(new Position(5, 5));

        heroController.moveHeroDown();

        Mockito.when(hero.getPosition()).thenReturn(new Position(0, 0));
        heroController.moveHeroUp();

        Mockito.verify(hero, Mockito.never()).setPosition(Mockito.any());
    }

    @Test
    public void itemColliding() {
        Mockito.when(arena.collidesWithBlocks(item.getPosition(hero.getPosition()), item.getDimensions())).thenReturn(true);
        Mockito.when(arena.collidesWithBlocks(hero.getPosition(), hero.getDimensions())).thenReturn(false);

        heroController.moveHeroLeft();
        heroController.moveHeroRight();

        Mockito.verify(hero, Mockito.never()).setPosition(Mockito.any());
    }

    @Test
    public void fallHero() {
        Mockito.when(arena.hasAdjacentBlock(Mockito.any(), Mockito.any())).thenReturn(false);

        heroController.fallHero();
        Mockito.verify(hero, Mockito.times(1)).setPosition(new Position(10, 11));
    }

    @Test
    public void isHeroAlive() {
        Mockito.when(hero.getStats()).thenReturn(new HeroStats(new Level(5, 0), 50, 5, 1, 10));
        Assertions.assertTrue(heroController.isHeroAlive());

        Mockito.when(hero.getStats()).thenReturn(new HeroStats(new Level(5, 0), 0, 5, 1, 10));
        Assertions.assertFalse(heroController.isHeroAlive());
    }

    @Test
    public void useTool() {
        Mockito.when(hero.targetWithinRange()).thenReturn(true);
        Mockito.when(hero.getTargetPosition()).thenReturn(new Position(5, 5));
        heroController.useItem();

        Mockito.verify(arena, Mockito.times(1)).breakBlock(hero.getTargetPosition(), (Tool) item);
    }

    @Test
    public void useBlockPlacer() {
        Mockito.when(hero.targetWithinRange()).thenReturn(true);
        Mockito.when(hero.getTargetPosition()).thenReturn(new Position(5, 5));
        Item placer = Mockito.mock(BlockPlacer.class);
        Mockito.when(toolbar.getActiveItem()).thenReturn(placer);

        heroController.useItem();
        Mockito.verify(arena, Mockito.times(1)).placeBlock(hero.getTargetPosition());
    }

    @Test
    public void useBuff() {
        Buff buff = Mockito.mock(Buff.class);
        Mockito.when(toolbar.getActiveItemIdx()).thenReturn(0);
        Mockito.when(toolbar.getActiveItem()).thenReturn(buff);

        try {
            heroController.useItem();
        } catch (NullPointerException ignored) {}

        Mockito.verify(toolbar, Mockito.times(1)).removeItem(0);
        Mockito.verify(hero, Mockito.times(1)).addBuff(buff);
    }

    @Test
    public void changeHeroSlot() {
        Mockito.when(item.getPosition(hero.getPosition())).thenReturn(new Position(5, 5));
        Mockito.when(toolbar.getItem(Mockito.anyInt())).thenReturn(item);
        Mockito.when(arena.collidesWithBlocks(item.getPosition(hero.getPosition()), item.getDimensions())).thenReturn(false);

        heroController.changeHeroSlot(1);
        Mockito.verify(toolbar, Mockito.times(1)).setActiveItemIdx(1);

        Mockito.when(arena.collidesWithBlocks(item.getPosition(hero.getPosition()), item.getDimensions())).thenReturn(true);
        heroController.changeHeroSlot(2);
        Mockito.verify(toolbar, Mockito.never()).setActiveItemIdx(2);
    }

    @Test
    public void doAction() {
        Mockito.when(hero.targetWithinRange()).thenReturn(true);
        Mockito.when(hero.getTargetPosition()).thenReturn(new Position(5, 5));

        HeroController spyController = Mockito.spy(heroController);

        spyController.doAction(GUI.ACTION.LEFT);
        spyController.doAction(GUI.ACTION.RIGHT);
        spyController.doAction(GUI.ACTION.UP);
        spyController.doAction(GUI.ACTION.DOWN);
        spyController.doAction(GUI.ACTION.PRESS);
        spyController.doAction(GUI.ACTION.CLICK);
        spyController.doAction(GUI.ACTION.SLOT0);
        spyController.doAction(GUI.ACTION.SLOT1);
        spyController.doAction(GUI.ACTION.SLOT2);
        spyController.doAction(GUI.ACTION.SLOT3);
        spyController.doAction(GUI.ACTION.SLOT4);
        spyController.doAction(GUI.ACTION.SLOT5);
        spyController.doAction(GUI.ACTION.SLOT6);
        spyController.doAction(GUI.ACTION.SLOT7);
        spyController.doAction(GUI.ACTION.SLOT8);
        spyController.doAction(GUI.ACTION.SLOT9);

        Mockito.verify(spyController, Mockito.times(1)).moveHeroLeft();
        Mockito.verify(spyController, Mockito.times(1)).moveHeroRight();
        Mockito.verify(spyController, Mockito.times(1)).moveHeroUp();
        Mockito.verify(spyController, Mockito.times(1)).moveHeroDown();
        Mockito.verify(spyController, Mockito.times(1)).useItem();
        Mockito.verify(arena, Mockito.times(1)).heroAttack(Mockito.any(), Mockito.any());
        Mockito.verify(spyController, Mockito.times(1)).changeHeroSlot(0);
        Mockito.verify(spyController, Mockito.times(1)).changeHeroSlot(1);
        Mockito.verify(spyController, Mockito.times(1)).changeHeroSlot(2);
        Mockito.verify(spyController, Mockito.times(1)).changeHeroSlot(3);
        Mockito.verify(spyController, Mockito.times(1)).changeHeroSlot(4);
        Mockito.verify(spyController, Mockito.times(1)).changeHeroSlot(5);
        Mockito.verify(spyController, Mockito.times(1)).changeHeroSlot(6);
        Mockito.verify(spyController, Mockito.times(1)).changeHeroSlot(7);
        Mockito.verify(spyController, Mockito.times(1)).changeHeroSlot(8);
        Mockito.verify(spyController, Mockito.times(1)).changeHeroSlot(9);
    }
}
