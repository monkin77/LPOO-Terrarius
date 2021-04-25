import GUI.LanternaGui;
import Viewer.ArenaViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    public static void main(String[] args) throws FontFormatException, IOException, URISyntaxException {
        LanternaGui gui = new LanternaGui(75, 75);
        ArenaViewer arenaViewer = new ArenaViewer(gui);

        for (int i = 0; i < 100; i++){
            gui.clear();
            arenaViewer.draw();
            gui.refresh();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Here we go");
    }
}
