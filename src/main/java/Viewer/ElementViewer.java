package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Viewer.Image.Image;

public abstract class ElementViewer {
    Image image;
    public abstract void draw(Element element, GUI gui);
}
