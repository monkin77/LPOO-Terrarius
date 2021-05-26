package Terrarius.Viewer.Image;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public abstract class Image{
    public abstract void load(String string);
    public abstract void update();
    public abstract void reset();
    public abstract void draw(Position position, Element.Orientation orientation, GUI gui);

    protected Scanner getScannerFromFile(String fname) throws FileNotFoundException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(fname);
        File imageFile = new File(resource.toURI());
        return new Scanner(imageFile);
    }
}
