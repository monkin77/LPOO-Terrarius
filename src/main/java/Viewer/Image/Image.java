package Viewer.Image;

import GUI.GUI;
import Model.elements.Element;

public abstract class Image{
    public abstract void load(String string);
    public abstract void update();
    public abstract void draw(Element element, GUI gui);
}
