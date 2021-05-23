package Terrarius.Controller.Game;

import Terrarius.Controller.Controller;
import Terrarius.GUI.GUI;
import Terrarius.Terrarius;
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
        this.arenaController = new ArenaController(new HeroController(arena), new EnemyController(arena), Terrarius.getMsPerUpdate());
    }

    @Override
    public void giveActions(Terrarius terrarius, List<GUI.ACTION> actions) {
        if (actions.contains(GUI.ACTION.QUIT))
            terrarius.setState(null);  //TODO: PUT MENU STATE HERE WHEN DONE
        else
            arenaController.addActions(actions);
    }

    @Override
    public void update(Terrarius terrarius) {
        arenaController.update();

        if (arenaController.checkEnd())  //TODO: LOSING/WINNING SCREEN
            terrarius.setState(null);
    }
}
