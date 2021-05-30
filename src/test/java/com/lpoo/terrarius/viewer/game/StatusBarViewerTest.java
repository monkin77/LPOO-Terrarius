package com.lpoo.terrarius.viewer.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Level;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.model.game.elements.hero.HeroStats;
import com.lpoo.terrarius.model.game.items.buffs.Buff;
import com.lpoo.terrarius.model.game.items.buffs.BuffStats;
import com.lpoo.terrarius.utils.Dimensions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

public class StatusBarViewerTest {
    private Hero hero;
    private GUI gui;
    private StatusBarViewer statusBarViewer;
    private Buff buff;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);
        buff = Mockito.mock(Buff.class);
        Mockito.when(buff.getComponentName()).thenReturn("Buff");
        Mockito.when(buff.getStats()).thenReturn(new BuffStats(10, 5, 2, 1, 2));

        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getStats()).thenReturn(new HeroStats(new Level(1, 0), 100, 5, 1, 8));
        Mockito.when(hero.getActiveBuffs()).thenReturn(Collections.singletonList(buff));

        statusBarViewer = new StatusBarViewer();
    }

    @Test
    public void draw() {
        statusBarViewer.draw(hero, new Dimensions(100, 100), gui);

        Mockito.verify(gui, Mockito.times(1)).drawString(Mockito.anyInt(), Mockito.anyInt(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        Mockito.verify(gui, Mockito.times(306)).drawCharacter(Mockito.anyInt(), Mockito.anyInt(),
                Mockito.anyChar(), Mockito.anyString(), Mockito.anyString());
    }
}
