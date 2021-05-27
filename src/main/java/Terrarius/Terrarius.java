package Terrarius;

import Terrarius.GUI.GUI;
import Terrarius.GUI.LanternaGui;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.arena.LoaderArenaBuilder;
import Terrarius.Model.Game.items.tools.Axe;
import Terrarius.Model.Menu.Menu;
import Terrarius.States.GameState;
import Terrarius.States.MenuState;
import Terrarius.States.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Terrarius {
    private static final int MS_PER_UPDATE = 16;

    private final GUI gui;
    private State state;

    public static void main(String[] args) throws FontFormatException, IOException, URISyntaxException {
        new Terrarius(128, 74).start();
    }

    public Terrarius(int width, int height) throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGui(width, height);
        this.state = new MenuState(new Menu());
    }

    /*
    Check this pattern at:
    https://gameprogrammingpatterns.com/game-loop.html
     */
    protected void start() throws IOException {
        long previous = System.currentTimeMillis();
        long lag = 0;

        while (true) {
            long current = System.currentTimeMillis();
            long elapsed = current - previous;
            previous = current;
            lag += elapsed;

            state.readInput(this, gui);

            while (lag >= MS_PER_UPDATE) {
                state.update(this);
                lag -= MS_PER_UPDATE;

                if (state == null) {
                    gui.close();
                    return;
                }
            }

            state.draw(gui);
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public static int getMsPerUpdate() {
        return MS_PER_UPDATE;
    }
}
