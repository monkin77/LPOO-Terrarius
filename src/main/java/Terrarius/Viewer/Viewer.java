package Terrarius.Viewer;

import Terrarius.GUI.GUI;

import java.io.IOException;

public abstract class Viewer<T> {
    public abstract void update();
    public abstract void draw(GUI gui, T model) throws IOException;
}
