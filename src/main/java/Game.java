import GUI.GUI;
import GUI.LanternaGui;
import Model.Position;
import Model.arena.Arena;
import Model.arena.DefaultArenaBuilder;
import Model.elements.Hero;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final GUI gui;
    private final Arena arena;

    public static void main(String[] args) throws FontFormatException, IOException, URISyntaxException {
        System.out.println("Here we go");

        Game game = new Game(150, 100);
    }

    public Game(int width, int height) throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGui(width, height);
        System.out.println("Here we go");

        this.arena = new DefaultArenaBuilder(width, height).createArena();
    }
}
