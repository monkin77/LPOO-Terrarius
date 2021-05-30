package Terrarius;
/*
TODO LAST IF THERE'S TIME
import Terrarius.States.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class TerrariusTest {
    private Terrarius terrarius;
    private State state;

    @BeforeEach
    public void setup() throws FontFormatException, IOException, URISyntaxException {
        state = Mockito.mock(State.class);

        terrarius = new Terrarius(10, 10);
        terrarius.setState(state);
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
                    terrarius.setState(null);
                return new Object();
            }
        }).when(state).update(Mockito.any());

        terrarius.start();

        Mockito.verify(state, Mockito.atLeastOnce()).readInput(Mockito.same(terrarius), Mockito.any());
        Mockito.verify(state, Mockito.atLeastOnce()).draw(Mockito.any());
        Mockito.verify(state, Mockito.atLeastOnce()).update(Mockito.same(terrarius));
    }
}
*/