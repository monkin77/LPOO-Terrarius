package com.lpoo.terrarius.controller;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.Terrarius;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void giveActions(Terrarius terrarius, GUI gui) throws IOException;
    public abstract void update(Terrarius terrarius) throws FileNotFoundException, URISyntaxException;
}
