package Terrarius.Viewer;

import Terrarius.GUI.GUI;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Viewer<T> {
    public abstract void draw(GUI gui, T model) throws IOException, URISyntaxException;
}
