package Terrarius.Controller.Game;

import Terrarius.Controller.Controller;
import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.arena.MultiMapArenaBuilder;
import Terrarius.Model.ItemShop.ItemShop;
import Terrarius.States.GameState;
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
            this.exitCurrentGame(terrarius);
        else if (actions.contains(GUI.ACTION.ITEM_SHOP)) {
            terrarius.setState(terrarius.getItemShopState());
        }
        else
            arenaController.addActions(actions);
        arenaController.setHeroTargetPosition(new Position(gui.getMouseX() / gui.getFontSize(), gui.getMouseY()/ gui.getFontSize()));
    }

    @Override
    public void update(Terrarius terrarius) {
        arenaController.update();
        if (arenaController.checkEnd())         //TODO: LOSING/WINNING SCREEN
            this.exitCurrentGame(terrarius);
    }

    public void resetGameState(Terrarius terrarius) {
        terrarius.setGameState(new GameState(new MultiMapArenaBuilder().createArena()));
        terrarius.setItemShopState( new ItemShopState(new ItemShop(((Arena) terrarius.getGameState().getModel()).getHero())));
    }

    public void exitCurrentGame(Terrarius terrarius) {
        terrarius.setState(new MenuState(new Menu()));
        this.resetGameState(terrarius);
    }
}
