package Terrarius.Controller.Menu;

import Terrarius.Controller.Controller;
import Terrarius.GUI.GUI;
import Terrarius.Terrarius;
import Terrarius.Model.Game.arena.LoaderArenaBuilder;
import Terrarius.Model.Menu.Menu;
import Terrarius.States.GameState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MenuController extends Controller<Menu> {
    private List<GUI.ACTION> actions;

    public MenuController(Menu menu) {
        super(menu);
        actions = new ArrayList<>();
    }

    @Override
    public void giveActions(Terrarius terrarius, GUI gui) throws IOException {
        this.actions = gui.getNextActions();
    }

    @Override
    public void update(Terrarius terrarius) throws FileNotFoundException, URISyntaxException {
        for (GUI.ACTION action : this.actions) {
            switch (action) {
                case UP_MENU:
                    getModel().previousOption();
                    break;
                case DOWN_MENU:
                    getModel().nextOption();
                    break;
                case SELECT:
                    if (getModel().isPlaySelected())
                        terrarius.setState(new GameState(new LoaderArenaBuilder(1).createArena()));
                    if (getModel().isQuitSelected())
                        terrarius.setState(null);
            }
        }
        actions.clear();
    }
}
