package com.lpoo.terrarius.states;

import com.lpoo.terrarius.controller.Controller;
import com.lpoo.terrarius.controller.game.GameController;
import com.lpoo.terrarius.model.game.arena.Arena;
import com.lpoo.terrarius.Terrarius;
import com.lpoo.terrarius.viewer.game.ArenaViewer;
import com.lpoo.terrarius.viewer.Viewer;

public class GameState extends State<Arena> {
    public GameState(Arena model) {
        super(model);
    }

    @Override
    protected Viewer<Arena> createViewer() {
        return new ArenaViewer();
    }

    @Override
    protected Controller<Arena> createController() {
        return new GameController(getModel());
    }

    @Override
    public void update(Terrarius terrarius) {
        super.update(terrarius);
        ((ArenaViewer) getViewer()).update();
    }
}
