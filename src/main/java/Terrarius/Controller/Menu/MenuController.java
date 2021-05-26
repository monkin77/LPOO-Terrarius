package Terrarius.Controller.Menu;

import Terrarius.Controller.Controller;
import Terrarius.GUI.GUI;
import Terrarius.Model.Game.arena.MultiMapArenaBuilder;
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
    private GUI.ACTION lastAction;

    public MenuController(Menu menu) {
        super(menu);
        actions = new ArrayList<>();
        lastAction = GUI.ACTION.NONE;
    }

    @Override
    public void giveActions(Terrarius terrarius, GUI gui) throws IOException {
        this.actions = gui.getNextActions();
    }

    @Override
    public void update(Terrarius terrarius) throws FileNotFoundException, URISyntaxException {
        if (!actions.contains(lastAction)) lastAction = GUI.ACTION.NONE;

        for (GUI.ACTION action : this.actions) {
            if (lastAction == action) continue;  // Menu shouldn't be spammable

            switch (action) {
                case UP:
                    getModel().previousOption();
                    break;
                case DOWN:
                    getModel().nextOption();
                    break;
                case SELECT:
                    if (getModel().isPlaySelected())
                        terrarius.setState(new GameState(new MultiMapArenaBuilder().createArena()));
                    if (getModel().isQuitSelected())
                        terrarius.setState(null);
            }
            lastAction = action;
        }
        actions.clear();
    }
}
