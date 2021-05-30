package Terrarius.Model.Game.items.buffs;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.hero.HeroStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class BuffTest {
    Buff buff;
    Hero hero;

    @BeforeEach
    public void createHero() {
        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getStats()).thenReturn(new HeroStats(new Level(6, 0), 100, 5, 1, 10));
    }

    @Test
    public void apple() throws FileNotFoundException, URISyntaxException {
        buff = new Buff(hero, "Apple");
        Assertions.assertEquals(10, buff.getStats().getAmountHP());
        Assertions.assertEquals(5, buff.getStats().getDuration());
    }

    @Test
    public void elasticPotion() throws FileNotFoundException, URISyntaxException {
        buff = new Buff(hero, "ElasticPotion");
        Assertions.assertEquals(2 + 6/10, buff.getStats().getExtraRange());
        Assertions.assertEquals(10 + 6/3, buff.getStats().getDuration());
    }

}
