import GUI.GUI;
import GUI.LanternaGui;
import Model.arena.ArenaBuilder;
import Viewer.ArenaViewer;
import Model.arena.Arena;
import Model.arena.DefaultArenaBuilder;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final GUI gui;
    private final Arena arena;

    public static void main(String[] args) throws FontFormatException, IOException, URISyntaxException {
        LanternaGui gui = new LanternaGui(128, 64);
        ArenaViewer arenaViewer = new ArenaViewer(gui);
        ArenaBuilder arenaBuilder = new DefaultArenaBuilder(128, 64);
        Arena arena = arenaBuilder.createArena();

        for (int i = 0; i < 100; i++){
            gui.clear();
            arenaViewer.draw(arena);
            gui.refresh();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Here we go");

        //Game game = new Game(150, 100);
    }

    public Game(int width, int height) throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGui(width, height);
        System.out.println("Here we go");

        this.arena = new DefaultArenaBuilder(width, height).createArena();
    }
}
