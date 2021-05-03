package Controller;

import GUI.GUI;
import Model.arena.Arena;
import Viewer.ArenaViewer;

import java.io.IOException;
import java.util.List;

/*
GameController will manage the whole map, which contains multiple arenas changing and being generated while
the player explores the game. This means that the arenaController will change!
 */
public class GameController {
    private static final int MS_PER_UPDATE = 16;

    private ArenaController arenaController;
    private ArenaViewer arenaViewer;
    private Arena arena;
    private GUI gui;

    public GameController(Arena arena, GUI gui) {
        this.arena = arena;
        this.arenaController = new ArenaController(this.arena, MS_PER_UPDATE);
        this.arenaViewer = new ArenaViewer(gui);
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

            List<GUI.ACTION> actions = gui.getNextActions();
            if (actions.contains(GUI.ACTION.QUIT)) break;

            for(GUI.ACTION action : actions){
                arenaController.doAction(action);
            }

            //if (arenaController.checkEnd()) break;

            while (lag >= MS_PER_UPDATE) {
                arenaController.timedActions();
                arenaViewer.update();
                lag -= MS_PER_UPDATE;
            }

            arenaViewer.draw(this.arena);
        }
    }
}
