package Terrarius;

import Terrarius.States.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

// TODO: This tests opens the terminal. Try to make it better
public class GameTest {
    private Game game;
    private State state;

    @BeforeEach
    public void setup() throws FontFormatException, IOException, URISyntaxException {
        state = Mockito.mock(State.class);

        game = new Game(10, 10);
        game.setState(state);
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
                    game.setState(null);
                return new Object();
            }
        }).when(state).draw(Mockito.any());

        game.start();

        Mockito.verify(state, Mockito.atLeastOnce()).readInput(Mockito.same(game), Mockito.any());
        Mockito.verify(state, Mockito.atLeastOnce()).draw(Mockito.any());
        Mockito.verify(state, Mockito.atLeastOnce()).update(Mockito.same(game));
    }
}
