package Terrarius.Controller;

import Terrarius.Controller.Game.ArenaController;
import Terrarius.Controller.Game.EnemyController;
import Terrarius.Controller.Game.HeroController;
import Terrarius.GUI.GUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ArenaControllerTest {
    private ArenaController arenaController;
    private HeroController heroController;
    private EnemyController enemyController;

    @BeforeEach
    public void setup() {
        enemyController = Mockito.mock(EnemyController.class);
        heroController = Mockito.mock(HeroController.class);
        arenaController = new ArenaController(heroController, enemyController, 16);
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
        for (int i = 0; i < 11; ++i)
            arenaController.update();

        Mockito.verify(enemyController, Mockito.times(1)).moveEnemies();
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
}
