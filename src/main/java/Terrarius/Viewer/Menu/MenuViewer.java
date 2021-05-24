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

        // TODO: GET SCREEN DIMENSIONS AND DRAW BASED ON THEM
        gui.clear();
        gui.drawString(50, 5, "Terrarius", "#FFFFFF", "#000000");

        for (int i = 0; i < menu.getNumOptions(); ++i)
            gui.drawString(50, 15+ i*10,
                            menu.getOption(i),
                    menu.isSelected(i) ? "#FFD700" : "#FFFFFF", "#000000");
        gui.refresh();
    }

    @Override
    public void update() {
        // Future animations
    }
}
