package Terrarius.States;

import Terrarius.Controller.Controller;
import Terrarius.Controller.Game.GameController;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Viewer.Game.ArenaViewer;
import Terrarius.Viewer.Viewer;

public class GameState extends State<Arena> {
    public GameState(Arena model) {
        super(model);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new ArenaViewer();
    }

    @Override
    protected Controller<Arena> getController() {
        return new GameController(getModel());
    }
}
