package Controller;

import GUI.GUI;
import Model.arena.Arena;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/*
GameController will manage the whole map, which contains multiple arenas changing and being generated while
the player explores the game. This means that the arenaController will change!
 */
public class GameController {
    private static final int MS_PER_FRAME = 16;
    private static final int MS_PER_ACTION = 64;

    private ArenaController arenaController;
    private GUI gui;
    private int frameCounter;

    // TO DO: ADD VIEWERS
    public GameController(Arena arena, GUI gui) {
        this.arenaController = new ArenaController(arena);
        this.gui = gui;
        this.frameCounter = 0;
    }

    /*
    Check this out better:
    https://gameprogrammingpatterns.com/game-loop.html
     */
    public void start() throws InterruptedException, IOException {
        final int framesPerAction = MS_PER_ACTION / MS_PER_FRAME;
        while (true) {
            Instant start = Instant.now();
            frameCounter++;

            if (frameCounter % framesPerAction == 0) {
                arenaController.timedActions();
                frameCounter = 0;
            }

            GUI.ACTION action = gui.getNextAction();
            if (action == GUI.ACTION.QUIT) break;

            arenaController.doAction(action);
            if (arenaController.checkEnd()) break;

            // TO DO: DRAW

            Duration timeElapsed = Duration.between(start, Instant.now());
            Thread.sleep(MS_PER_FRAME - timeElapsed.toMillis());
        }
    }
}
