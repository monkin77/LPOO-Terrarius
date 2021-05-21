package Terrarius.Controller.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.items.Toolbar;
import Terrarius.Model.Game.items.tools.Axe;
import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.elements.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HeroControllerTest {
    private HeroController heroController;
    private Hero hero;
    private Arena arena;

    @BeforeEach
    public void setup() {
        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getDimensions()).thenReturn(new Dimensions(5, 5));
        Mockito.when(hero.getPosition()).thenReturn(new Position(10, 10));

        arena = Mockito.mock(Arena.class);

        Mockito.when(arena.getHeight()).thenReturn(100);
        Mockito.when(arena.getWidth()).thenReturn(100);
        Mockito.when(arena.getHero()).thenReturn(hero);
        Mockito.when(arena.collides(Mockito.any(), Mockito.any())).thenReturn(false);

        heroController = new HeroController(arena);
    }

    @Test
    public void moveHeroLeft() {
        heroController.moveHeroLeft();
        Mockito.verify(hero, Mockito.times(1)).setPosition(new Position(9, 10));
    }

    @Test
    public void moveHeroRight() {
        heroController.moveHeroRight();
        Mockito.verify(hero, Mockito.times(1)).setPosition(new Position(11, 10));
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
        Mockito.when(arena.getWidth()).thenReturn(10);
        Mockito.when(arena.getHeight()).thenReturn(10);

        heroController.moveHeroRight();
        heroController.moveHeroDown();

        Mockito.when(hero.getPosition()).thenReturn(new Position(0, 0));

        heroController.moveHeroLeft();
        heroController.moveHeroUp();

        Mockito.verify(hero, Mockito.never()).setPosition(Mockito.any());
    }

    @Test
    public void moveHeroWhenColliding() {
        Mockito.when(arena.collides(Mockito.any(), Mockito.any())).thenReturn(true);

        heroController.moveHeroUp();
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
        Mockito.when(hero.getHealth()).thenReturn(50);
        Assertions.assertTrue(heroController.isHeroAlive());

        Mockito.when(hero.getHealth()).thenReturn(0);
        Assertions.assertFalse(heroController.isHeroAlive());
    }

    @Test
    public void doAction() {
        // TODO: OTHER ACTIONS WHEN READY
        HeroController spyController = Mockito.spy(heroController);

        spyController.doAction(GUI.ACTION.LEFT);
        spyController.doAction(GUI.ACTION.RIGHT);
        spyController.doAction(GUI.ACTION.UP);
        spyController.doAction(GUI.ACTION.SLOT0);

        Mockito.verify(spyController, Mockito.times(1)).moveHeroLeft();
        Mockito.verify(spyController, Mockito.times(1)).moveHeroRight();
        Mockito.verify(spyController, Mockito.times(1)).moveHeroUp();
        Mockito.verify(spyController, Mockito.times(1)).changeHeroSlot(0);
    }

    @Test
    public void checkToolCollisionWhenActive() {
        Axe axe = Mockito.mock(Axe.class);
        Mockito.when(axe.getPosition(Mockito.any())).thenReturn(new Position(5,5));

        Toolbar tb = Mockito.mock(Toolbar.class);
        Mockito.when(tb.getActiveItem()).thenReturn(axe);
        Mockito.when(hero.getToolBar()).thenReturn(tb);

        heroController.moveHeroLeft();
        Mockito.verify(arena, Mockito.times(2)).collides(Mockito.any(), Mockito.any());
    }
}
