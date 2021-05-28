package Terrarius;

import Terrarius.GUI.GUI;
import Terrarius.GUI.LanternaGui;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.arena.LoaderArenaBuilder;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.tools.Axe;
import Terrarius.Model.ItemShop.ItemShop;
import Terrarius.Model.Menu.Menu;
import Terrarius.States.GameState;
import Terrarius.States.ItemShopState;
import Terrarius.States.MenuState;
import Terrarius.States.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Terrarius {
    private static final int MS_PER_UPDATE = 16;

    private final GUI gui;
    private State state;

    private State gameState;
    private State itemShopState;

    public static void main(String[] args) throws FontFormatException, IOException, URISyntaxException {
        new Terrarius(128, 74).start();
    }

    public Terrarius(int width, int height) throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGui(width, height);
        this.state = new MenuState(new Menu());
        this.gameState = new GameState(new LoaderArenaBuilder(1).createArena());
        this.itemShopState = new ItemShopState(new ItemShop(((Arena) gameState.getModel()).getHero()));
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
            
            while (lag >= MS_PER_UPDATE) {
                state.readInput(this, gui);
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

    public static int getMsPerUpdate() {
        return MS_PER_UPDATE;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getGameState() {
        return gameState;
    }

    public State getItemShopState() {
        return itemShopState;
    }

    public GUI getGui() {
        return gui;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public void setItemShopState(State itemShopState) {
        this.itemShopState = itemShopState;
    }
}

