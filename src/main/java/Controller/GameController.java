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
    private static final int MS_PER_UPDATE = 16;

    private ArenaController arenaController;
    private GUI gui;

    // TO DO: ADD VIEWERS
    public GameController(Arena arena, GUI gui) {
        this.arenaController = new ArenaController(arena);
        this.gui = gui;
    }

    /*
    Check this pattern at:
    https://gameprogrammingpatterns.com/game-loop.html
     */
    long previous = System.currentTimeMillis();
    long lag = 0;
    public void start() throws IOException {
        while (true) {
            long current = System.currentTimeMillis();
            long elapsed = current - previous;
            previous = current;
            lag += elapsed;

            GUI.ACTION action = gui.getNextAction();
            if (action == GUI.ACTION.QUIT) break;

            arenaController.doAction(action);
            if (arenaController.checkEnd()) break;

            while (lag >= MS_PER_UPDATE) {
                arenaController.timedActions();
                lag -= MS_PER_UPDATE;
            }

            // TO DO: DRAW
        }
    }
}
