import GUI.LanternaGui;
import Viewer.MasterViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    public static void main(String[] args) throws FontFormatException, IOException, URISyntaxException {
        LanternaGui gui = new LanternaGui(75, 75);
        MasterViewer masterViewer = new MasterViewer(gui);
        masterViewer.draw();
        gui.refresh();
        System.out.println("Here we go");
    }
}
