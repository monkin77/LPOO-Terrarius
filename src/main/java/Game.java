import Controller.GameController;
import GUI.GUI;
import GUI.LanternaGui;
import Model.arena.Arena;
import Model.arena.LoaderArenaBuilder;
import Model.items.tools.Axe;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static Viewer.ViewerConstants.SCREEN_HEIGHT;
import static Viewer.ViewerConstants.SCREEN_WIDTH;

public class Game {
    private final GUI gui;
    private final Arena arena;

    public static void main(String[] args) throws FontFormatException, IOException, URISyntaxException {
        new Game(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public Game(int width, int height) throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGui(width, height);
        this.arena = new LoaderArenaBuilder(1).createArena();
        this.arena.getHero().addItem(1, new Axe(this.arena.getHero()));
        this.arena.getHero().addItem(3, new Axe(this.arena.getHero()));

        GameController controller = new GameController(arena, gui);

        controller.start();

    }
}
