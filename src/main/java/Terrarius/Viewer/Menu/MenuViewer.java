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

        // TODO: GET SCREEN DIMENSIONS AND DRAW BASED ON THEM
        gui.clear();
        gui.drawString(xPos, yStart, "Terrarius", "#FFFFFF", "#000000");

        for (int i = 0; i < menu.getNumOptions(); ++i)
            gui.drawString(xPos, yStart + (i + 1) * yDist,
                            menu.getOption(i),
                    menu.isSelected(i) ? "#FFD700" : "#FFFFFF", "#000000");
        gui.refresh();
    }

    @Override
    public void update() {
        // Future animations
    }
}
