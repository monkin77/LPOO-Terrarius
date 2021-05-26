package Controller;

import GUI.GUI;
import Model.arena.Arena;
import Viewer.ArenaViewer;

import java.io.IOException;
import java.util.List;

public class GameController {
    private static final int MS_PER_UPDATE = 16;

    private ArenaController arenaController;
    private ArenaViewer arenaViewer;
    private Arena arena;  // Arena will change mid-game, in the future
    private GUI gui;

    public GameController(Arena arena, GUI gui) {
        this.arena = arena;
        this.arenaController = new ArenaController(new HeroController(arena), new EnemyController(arena), MS_PER_UPDATE);
        this.arenaViewer = new ArenaViewer(gui);
        this.gui = gui;
    }

    /*
    Check this pattern at:
    https://gameprogrammingpatterns.com/game-loop.html
     */
    public void start() throws IOException {
        long previous = System.currentTimeMillis();
        long lag = 0;

        while (true) {
            long current = System.currentTimeMillis();
            long elapsed = current - previous;
            previous = current;
            lag += elapsed;

            if (readInputAndCheckExit())
                return;

            while (lag >= MS_PER_UPDATE) {
                if (updateAndCheckEnd()) return;
                lag -= MS_PER_UPDATE;
            }

            draw();
        }
    }

    protected boolean readInputAndCheckExit() throws IOException {
        List<GUI.ACTION> actions = gui.getNextActions();

        if (actions.contains(GUI.ACTION.QUIT))
            return true;

        arenaController.addActions(actions);
        return false;
    }

    protected boolean updateAndCheckEnd() {
        arenaController.update();
        arenaViewer.update();
        arena.update();

        if (arenaController.checkEnd())
            return true;
        return false;
    }

    protected void draw() throws IOException {
        arenaViewer.draw(this.arena);
    }
}
