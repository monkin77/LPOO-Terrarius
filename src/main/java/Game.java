import Controller.GameController;
import GUI.GUI;
import GUI.LanternaGui;
import Model.arena.Arena;
import Model.arena.DefaultArenaBuilder;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final GUI gui;
    private final Arena arena;

    public static void main(String[] args) throws FontFormatException, IOException, URISyntaxException {
        Game game = new Game(128, 64);
    }

    public Game(int width, int height) throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGui(width, height);
        this.arena = new DefaultArenaBuilder(width, height).createArena();
        GameController controller = new GameController(arena, gui);

        controller.start();

    }
}
