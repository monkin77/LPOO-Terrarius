package Terrarius.Controller.Game;

import Terrarius.Controller.Controller;
import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.SkillTree.SkillTree;
import Terrarius.States.GameState;
import Terrarius.States.SkillTreeState;
import Terrarius.Model.ItemShop.ItemShop;
import Terrarius.States.ItemShopState;
import Terrarius.Terrarius;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Menu.Menu;
import Terrarius.States.MenuState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class GameController extends Controller<Arena> {
    private final ArenaController arenaController;

    public GameController(Arena arena, ArenaController arenaController) {
        super(arena);
        this.arenaController = arenaController;
    }

    public GameController(Arena arena) {
        super(arena);
        this.arenaController = new ArenaController(arena, new HeroController(arena), new EnemyController(arena), Terrarius.getMsPerUpdate());
    }

    @Override
    public void giveActions(Terrarius terrarius, GUI gui) throws IOException {
        List<GUI.ACTION> actions = gui.getNextActions();
        if (actions.contains(GUI.ACTION.QUIT))
            terrarius.setState(terrarius.createMenuState());
        else if (actions.contains(GUI.ACTION.SKILL_TREE))
            terrarius.setState(terrarius.createSkillTreeState());
        else if (actions.contains(GUI.ACTION.ITEM_SHOP))
            terrarius.setState(terrarius.createItemShopState());
        else
            arenaController.addActions(actions);
        arenaController.setHeroTargetPosition(new Position(gui.getMouseX() / gui.getFontSize(), gui.getMouseY()/ gui.getFontSize()));
    }

    @Override
    public void update(Terrarius terrarius) {
        arenaController.update();
        if (arenaController.checkEnd())
            terrarius.setState(new MenuState(new Menu()));
    }
}
