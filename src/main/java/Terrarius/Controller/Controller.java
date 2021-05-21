package Terrarius.Controller;

import Terrarius.GUI.GUI;
import Terrarius.Terrarius;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void giveActions(Terrarius terrarius, List<GUI.ACTION> actions);
    public abstract void update(Terrarius terrarius) throws FileNotFoundException, URISyntaxException;
}
