package Terrarius.Controller.Menu;

import Terrarius.Controller.Controller;
import Terrarius.GUI.GUI;
import Terrarius.Terrarius;
import Terrarius.Model.Game.arena.LoaderArenaBuilder;
import Terrarius.Model.Menu.Menu;
import Terrarius.States.GameState;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MenuController extends Controller<Menu> {
    private final List<GUI.ACTION> actions;

    public MenuController(Menu menu) {
        super(menu);
        actions = new ArrayList<>();
    }

    @Override
    public void giveActions(Terrarius terrarius, List<GUI.ACTION> actions) {
        for (GUI.ACTION action : actions)
            if (!this.actions.contains(action))
                this.actions.add(action);
    }

    @Override
    public void update(Terrarius terrarius) throws FileNotFoundException, URISyntaxException {
        for (GUI.ACTION action : this.actions)
            switch (action) {
                case UP:
                    getModel().previousOption();
                    break;
                case DOWN:
                    getModel().nextOption();
                    break;
                case SELECT:
                    if (getModel().isPlaySelected())
                        terrarius.setState(new GameState(new LoaderArenaBuilder(1).createArena()));
                    if (getModel().isQuitSelected())
                        terrarius.setState(null);
            }
    }
}
