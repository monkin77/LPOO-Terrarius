package Terrarius;

import Terrarius.GUI.GUI;
import Terrarius.GUI.LanternaGui;
import Terrarius.Model.arena.Arena;
import Terrarius.Model.arena.LoaderArenaBuilder;
import Terrarius.Model.items.tools.Axe;
import Terrarius.States.GameState;
import Terrarius.States.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private static final int MS_PER_UPDATE = 16;

    private final GUI gui;
    private State state;

    public static void main(String[] args) throws FontFormatException, IOException, URISyntaxException {
        new Game(128, 64).start();
    }

    public Game(int width, int height) throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGui(width, height);

        Arena arena = new LoaderArenaBuilder(1).createArena();
        arena.getHero().addItem(1, new Axe(arena.getHero()));
        arena.getHero().addItem(3, new Axe(arena.getHero()));
        this.state = new GameState(arena);
    }

    /*
    Check this pattern at:
    https://gameprogrammingpatterns.com/game-loop.html
     */
    private void start() throws IOException {
        long previous = System.currentTimeMillis();
        long lag = 0;

        while (this.state != null) {
            long current = System.currentTimeMillis();
            long elapsed = current - previous;
            previous = current;
            lag += elapsed;

            state.readInput(this, gui);

            while (lag >= MS_PER_UPDATE) {
                state.update(this);
                lag -= MS_PER_UPDATE;
            }

            state.draw(gui);
        }
        gui.close();
    }

    public void setState(State state) {
        this.state = state;
    }

    public static int getMsPerUpdate() {
        return MS_PER_UPDATE;
    }
}
