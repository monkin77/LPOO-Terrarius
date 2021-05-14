package Terrarius.Controller.Game;

import Terrarius.Controller.Game.ArenaController;
import Terrarius.Controller.Game.GameController;
import Terrarius.GUI.GUI;
import Terrarius.Game;
import Terrarius.Model.arena.Arena;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameControllerTest {
    private GameController gameController;
    private ArenaController arenaController;
    private Game game;
    private Arena arena;

    @BeforeEach
    public void setup() {
        arenaController = Mockito.mock(ArenaController.class);
        arena = Mockito.mock(Arena.class);
        game = Mockito.mock(Game.class);
        gameController = new GameController(arena, arenaController);
    }

    @Test
    public void giveActions() {
        List<GUI.ACTION> actions = Arrays.asList(GUI.ACTION.RIGHT, GUI.ACTION.UP);
        gameController.giveActions(game, actions);
        Mockito.verify(arenaController, Mockito.times(1)).addActions(actions);

        actions = Arrays.asList(GUI.ACTION.QUIT);
        gameController.giveActions(game, actions);
        Mockito.verify(arenaController, Mockito.never()).addActions(actions);
        Mockito.verify(game, Mockito.times(1)).setState(null);
    }

    @Test
    public void update() {
        Mockito.when(arenaController.checkEnd()).thenReturn(false);

        gameController.update(game);
        Mockito.verify(arenaController, Mockito.times(1)).update();
        Mockito.verify(game, Mockito.never()).setState(Mockito.any());

        Mockito.when(arenaController.checkEnd()).thenReturn(true);

        gameController.update(game);
        Mockito.verify(arenaController, Mockito.times(2)).update();
        Mockito.verify(game, Mockito.times(1)).setState(null);
    }
    /*
    private Arena arena;
    private GUI gui;

    @BeforeEach
    public void setup() {
        arena = Mockito.mock(Arena.class);
        gui = Mockito.mock(GUI.class);
        gameController = new GameController(arena);
    }

    @Test
    public void readInputFromGUI() throws IOException {
        Assertions.assertFalse(gameController.readInputAndCheckExit());
        Mockito.verify(gui, Mockito.times(1)).getNextActions();
    }

    @Test
    public void checkExit() throws IOException {
        Mockito.when(gui.getNextActions()).thenReturn(Arrays.asList(GUI.ACTION.QUIT));

        Assertions.assertTrue(gameController.readInputAndCheckExit());
        Mockito.verify(gui, Mockito.times(1)).getNextActions();
    }

    @Test
    public void start() throws IOException {
        Mockito.doAnswer(new Answer() {
            private boolean first = true;
            private long start;
            private long current;

            @Override
            public Object answer(InvocationOnMock invocation) {
                if (first) {
                    start = System.currentTimeMillis();
                    first = false;
                }

                current = System.currentTimeMillis();
                if (current - start > 30)
                    return Arrays.asList(GUI.ACTION.QUIT);

                return Arrays.asList();
            }
        }).when(gui).getNextActions();

        gameController = Mockito.spy(gameController);
        Mockito.doReturn(false).when(gameController).updateAndCheckEnd();
        Mockito.doNothing().when(gameController).draw();

        gameController.start();

        Mockito.verify(gameController, Mockito.atLeastOnce()).readInputAndCheckExit();
        Mockito.verify(gameController, Mockito.atLeastOnce()).draw();
        Mockito.verify(gameController, Mockito.atLeastOnce()).updateAndCheckEnd();
    }
     */
}
