package Terrarius.Model.items.food;

import Terrarius.Model.Level;
import Terrarius.Model.elements.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FoodTest {
    Food food;
    Hero hero;

    @BeforeEach
    public void createHero() {
        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getLevel()).thenReturn(new Level(6, 0));
    }

    @Test
    public void apple() {
        food = new Apple(hero);
        Assertions.assertEquals(10, food.getStats().getAmountHP());
        Assertions.assertEquals(9, food.getStats().getDuration());
    }

    @Test
    public void banana() {
        food = new Banana(hero);
        Assertions.assertEquals(30, food.getStats().getAmountHP());
        Assertions.assertEquals(20, food.getStats().getDuration());
    }
}
