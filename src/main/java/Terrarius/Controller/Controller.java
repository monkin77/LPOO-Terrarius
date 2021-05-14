package Terrarius.Controller;

import Terrarius.GUI.GUI;
import Terrarius.Game;

import java.util.List;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void giveActions(Game game, List<GUI.ACTION> actions);
    public abstract void update(Game game);
}
