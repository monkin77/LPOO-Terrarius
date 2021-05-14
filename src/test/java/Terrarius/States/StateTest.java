package Terrarius.States;
/*
import Terrarius.Controller.Game.GameController;
import Terrarius.GUI.GUI;
import Terrarius.Game;
import Terrarius.Model.arena.Arena;
import Terrarius.Viewer.Game.ArenaViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StateTest {
    private State state;
    private Arena arena;
    private GameController gameController;
    private ArenaViewer arenaViewer;
    private GUI gui;
    private Game game;

    @BeforeEach
    public void setup() throws IOException {
        arena = Mockito.mock(Arena.class);
        gameController = Mockito.mock(GameController.class);
        arenaViewer = Mockito.mock(ArenaViewer.class);

        gui = Mockito.mock(GUI.class);

        game = Mockito.mock(Game.class);

        state = Mockito.mock(State.class);
        Mockito.when(state.getController()).thenReturn(gameController);
        Mockito.when(state.getViewer()).thenReturn(arenaViewer);

    }

    @Test
    public void readInput() throws IOException {
        List<GUI.ACTION> actions = Arrays.asList(GUI.ACTION.UP, GUI.ACTION.RIGHT);
        Mockito.when(gui.getNextActions()).thenReturn(actions);

        state.readInput(game, gui);
        Mockito.verify(gameController, Mockito.times(1)).giveActions(game, actions);
    }
}
TODO STATE REFACTOR SO WE CAN TEST IT EASILY
*/