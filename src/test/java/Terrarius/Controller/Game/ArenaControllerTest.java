package Terrarius.Controller.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.arena.MapZone;
import Terrarius.Model.Game.elements.hero.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArenaControllerTest {
    private ArenaController arenaController;
    private HeroController heroController;
    private EnemyController enemyController;
    private Hero hero;
    private MapZone mapZone;
    private List<MapZone> list;
    private Arena arena;
    private Position leftPos;
    private Position rightPos;

    @BeforeEach
    public void setup() {
        leftPos = new Position(0, 0);
        rightPos = new Position(10, 10);

        enemyController = Mockito.mock(EnemyController.class);
        heroController = Mockito.mock(HeroController.class);

        mapZone = Mockito.mock(MapZone.class);
        Mockito.when(mapZone.getLeftSpawn()).thenReturn(leftPos);
        Mockito.when(mapZone.getRightSpawn()).thenReturn(rightPos);
        list = new ArrayList<>();
        list.add(mapZone);

        hero = Mockito.mock(Hero.class);
        arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getHero()).thenReturn(hero);
        Mockito.when(arena.getMapZoneList()).thenReturn(list);

        arenaController = new ArenaController(arena, heroController, enemyController, 16);
    }

    @Test
    public void inputActions() {
        List<GUI.ACTION> actions = Arrays.asList(GUI.ACTION.UP, GUI.ACTION.DOWN, GUI.ACTION.LEFT, GUI.ACTION.RIGHT);
        arenaController.addActions(actions);
        arenaController.update();

        for (GUI.ACTION action : actions)
            Mockito.verify(heroController, Mockito.times(1)).doAction(action);
    }

    @Test
    public void enemyActions() {
        for (int i = 0; i < 50; ++i)
            arenaController.update();

        Mockito.verify(enemyController, Mockito.times(1)).damageHero();
        Mockito.verify(enemyController, Mockito.times(6)).moveEnemies();

    }

    @Test
    public void gravityActions() {
        arenaController.update();
        Mockito.verify(heroController, Mockito.times(1)).fallHero();
        Mockito.verify(enemyController, Mockito.times(1)).fallEnemies();

        arenaController.update();
        Mockito.verify(heroController, Mockito.times(2)).fallHero();
        Mockito.verify(enemyController, Mockito.times(2)).fallEnemies();
    }

    @Test
    public void checkEnd() {
        Mockito.when(heroController.isHeroAlive()).thenReturn(true);
        Assertions.assertFalse(arenaController.checkEnd());

        Mockito.when(heroController.isHeroAlive()).thenReturn(false);
        Assertions.assertTrue(arenaController.checkEnd());
    }

    @Test
    public void updateMapZone() {
        Mockito.when(arena.checkMapZoneAndUpdate()).thenReturn(Arena.BOUNDARY.MAP_SAME);
        arenaController.updateMapZone();
        Mockito.verify(hero, Mockito.never()).setPosition(Mockito.any());

        Mockito.when(arena.checkMapZoneAndUpdate()).thenReturn(Arena.BOUNDARY.MAP_LEFT);
        arenaController.updateMapZone();
        Mockito.verify(hero, Mockito.times(1)).setPosition(rightPos);

        Mockito.when(arena.checkMapZoneAndUpdate()).thenReturn(Arena.BOUNDARY.MAP_RIGHT);
        arenaController.updateMapZone();
        Mockito.verify(hero, Mockito.times(1)).setPosition(leftPos);
    }
}
