package com.lpoo.terrarius.viewer.image;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public abstract class Image{
    public abstract void load(String string) throws FileNotFoundException, URISyntaxException;
    public abstract void draw(Position position, Element.Orientation orientation, GUI gui);

    protected Scanner getScannerFromFile(String fname) throws FileNotFoundException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(fname);
        File imageFile = new File(resource.toURI());
        return new Scanner(imageFile);
    }
}
