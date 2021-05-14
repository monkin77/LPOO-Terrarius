package Terrarius.Controller.Game;

import Terrarius.Controller.Controller;
import Terrarius.GUI.GUI;
import Terrarius.Game;
import Terrarius.Model.arena.Arena;
import java.util.List;

public class GameController extends Controller<Arena> {
    private ArenaController arenaController;

    public GameController(Arena arena, ArenaController arenaController) {
        super(arena);
        this.arenaController = arenaController;
    }

    public GameController(Arena arena) {
        super(arena);
        this.arenaController = new ArenaController(new HeroController(arena), new EnemyController(arena), Game.getMsPerUpdate());
    }

    @Override
    public void giveActions(Game game, List<GUI.ACTION> actions) {
        if (actions.contains(GUI.ACTION.QUIT))
            game.setState(null);  //TODO: PUT MENU STATE HERE WHEN DONE
        else
            arenaController.addActions(actions);
    }

    @Override
    public void update(Game game) {
        arenaController.update();

        if (arenaController.checkEnd())  //TODO: LOSING/WINNING SCREEN
            game.setState(null);
    }
}
