package Terrarius.Viewer.Menu;

import Terrarius.GUI.GUI;
import Terrarius.Model.Menu.Menu;
import Terrarius.Viewer.Viewer;

import java.io.IOException;

public class MenuViewer extends Viewer<Menu> {
    private int previousSelected = -1;

    @Override
    public void draw(GUI gui, Menu menu) throws IOException {
        if (menu.getSelectedIndex() == previousSelected) return;
        previousSelected = menu.getSelectedIndex();

        int xPos = 7 * gui.getWidth() / 15;
        int yStart = gui.getWidth() / 8;
        int yDist = gui.getWidth() / 13;

        gui.clear();

        for (int x = 0; x < gui.getWidth(); ++x)
            for (int y = 0; y < gui.getHeight(); ++y)
                gui.drawCharacter(x, y, ' ', "#0000FF", "#0a489c");

        gui.drawString(xPos, yStart, "Terrarius", "#00ff00", "#0a489c");

        for (int i = 0; i < menu.getNumOptions(); ++i)
            gui.drawString(xPos, yStart + (i + 1) * yDist,
                            menu.getOption(i),
                    menu.isSelected(i) ? "#ff9b02" : "#FFFFFF", "#0a489c");
        gui.refresh();
    }

    @Override
    public void update() {
        // Menu is static
    }
}
