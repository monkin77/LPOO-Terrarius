package Terrarius.Controller.Game;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.Game.items.buffs.Buff;
import Terrarius.Model.Game.items.buffs.BuffStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BuffControllerTest {
    private BuffController buffController;
    private Buff buff;
    private Hero hero;

    @BeforeEach
    public void setup() {
        buff = Mockito.mock(Buff.class);
        Mockito.when(buff.getStats()).thenReturn(new BuffStats(5, 10, 2, 1, 3));

        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getStats()).thenReturn(new HeroStats(new Level(1, 0), 50, 3, 2, 5));

        buffController = new BuffController(buff, hero);
    }

    @Test
    public void initialBuffs() {
        Buff buff2;

        buff2 = Mockito.mock(Buff.class);
        Mockito.when(buff2.getStats()).thenReturn(new BuffStats(0, 10, 0, 0, 0));
        new BuffController(buff2, hero);

        Mockito.verify(hero, Mockito.times(1)).setPower(5);
        Mockito.verify(hero, Mockito.times(1)).setRange(8);
        Mockito.verify(hero, Mockito.times(1)).setSpeed(3);
        Mockito.verify(hero, Mockito.times(1)).setHealth(60);
    }

    @Test
    public void update() {
        Mockito.verify(hero, Mockito.times(1)).setHealth(52);

        buffController.updateAndCheckDuration(1000);
        Mockito.verify(hero, Mockito.times(2)).setHealth(52);
        Assertions.assertEquals(4, buff.getStats().getDuration());
    }

    @Test
    public void buffOver() {
        for (int i = 0; i < 5; ++i)
            buffController.updateAndCheckDuration(1000);

        Mockito.verify(hero, Mockito.times(1)).setPower(1);
        Mockito.verify(hero, Mockito.times(1)).setRange(2);
        Mockito.verify(hero, Mockito.times(1)).setSpeed(1);
        Mockito.verify(hero, Mockito.times(1)).removeBuff(buff);
    }
}
