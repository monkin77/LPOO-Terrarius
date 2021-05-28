/*
package Terrarius.Controller.Game;

import Terrarius.GUI.GUI;
import Terrarius.States.MenuState;
import Terrarius.Terrarius;
import Terrarius.Model.Game.arena.Arena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameControllerTest {
    private GameController gameController;
    private ArenaController arenaController;
    private Terrarius terrarius;
    private Arena arena;
    private GUI gui;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);
        Mockito.when(gui.getMouseX()).thenReturn(10);
        Mockito.when(gui.getMouseY()).thenReturn(20);
        Mockito.when(gui.getFontSize()).thenReturn(10);

        arenaController = Mockito.mock(ArenaController.class);
        arena = Mockito.mock(Arena.class);
        terrarius = Mockito.mock(Terrarius.class);
        gameController = new GameController(arena, arenaController);
    }

    @Test
    public void giveActions() throws IOException {

        List<GUI.ACTION> actions= Arrays.asList(GUI.ACTION.RIGHT, GUI.ACTION.UP);
        Mockito.when(gui.getNextActions()).thenReturn(actions);

        gameController.giveActions(terrarius, gui);
        Mockito.verify(arenaController, Mockito.times(1)).addActions(actions);
        Mockito.verify(arenaController, Mockito.times(1)).setHeroTargetPosition(Mockito.any());

        actions = Arrays.asList(GUI.ACTION.QUIT);
        Mockito.when(gui.getNextActions()).thenReturn(actions);

        gameController.giveActions(terrarius, gui);
        Mockito.verify(arenaController, Mockito.never()).addActions(actions);
        Mockito.verify(terrarius, Mockito.times(1)).
                setState(Mockito.argThat(state -> state instanceof MenuState));
    }

    @Test
    public void update() {
        Mockito.when(arenaController.checkEnd()).thenReturn(false);

        gameController.update(terrarius);
        Mockito.verify(arenaController, Mockito.times(1)).update();
        Mockito.verify(terrarius, Mockito.never()).setState(Mockito.any());

        Mockito.when(arenaController.checkEnd()).thenReturn(true);

        gameController.update(terrarius);
        Mockito.verify(arenaController, Mockito.times(2)).update();
        Mockito.verify(terrarius, Mockito.times(1)).setState(Mockito.any());
    }
}
*/