package Terrarius.States;

import Terrarius.Controller.Controller;
import Terrarius.GUI.GUI;
import Terrarius.Terrarius;
import Terrarius.Viewer.Viewer;

import java.io.IOException;
import java.util.List;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model) {
        this.model = model;
        this.controller = getController();
        this.viewer = getViewer();
    }

    public T getModel() {
        return model;
    }

    public void readInput(Terrarius terrarius, GUI gui) throws IOException {
        List<GUI.ACTION> actions = gui.getNextActions();
        controller.giveActions(terrarius, actions);
    }

    public void update(Terrarius terrarius) {
        controller.update(terrarius);
        viewer.update();
    }

    public void draw(GUI gui) throws IOException {
        viewer.draw(gui, model);
    }

    protected abstract Viewer<T> getViewer();
    protected abstract Controller<T> getController();
}