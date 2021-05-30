package com.lpoo.terrarius.viewer;

import com.lpoo.terrarius.gui.GUI;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Viewer<T> {
    public abstract void draw(GUI gui, T model) throws IOException, URISyntaxException;
}
