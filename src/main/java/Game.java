import Controller.GameController;
import GUI.GUI;
import GUI.LanternaGui;
import Model.Level;
import Model.Position;
import Model.arena.Arena;
import Model.arena.LoaderArenaBuilder;
import Model.arena.MultiMapArenaBuilder;
import Model.elements.Hero;
import Model.items.tools.Axe;
import Model.map.MapChooser;
import Model.map.MapZone;

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
        this.arena = new MultiMapArenaBuilder().createArena();
        this.arena.getHero().addItem(1, new Axe(this.arena.getHero()));
        this.arena.getHero().addItem(3, new Axe(this.arena.getHero()));
        GameController controller = new GameController(arena, gui);

        controller.start();

    }
}
