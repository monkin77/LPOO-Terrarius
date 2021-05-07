package Viewer.Image;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;

// TODO: REFACTOR ON ALL IMAGES
public abstract class Image{
    public abstract void load(String string);
    public abstract void update();
    public abstract void reset();
    public abstract void draw(Position position, Element.Orientation orientation, GUI gui);
}
