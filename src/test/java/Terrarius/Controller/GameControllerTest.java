package Terrarius.Controller;

import Terrarius.Controller.Game.GameController;
import Terrarius.GUI.GUI;
import Terrarius.Model.arena.Arena;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.Arrays;

public class GameControllerTest {
    private GameController gameController;
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
}
