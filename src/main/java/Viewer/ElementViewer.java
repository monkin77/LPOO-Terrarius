package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Viewer.Image.Image;
import Viewer.Image.StillImage;

public abstract class ElementViewer {
    protected Image image;
    public abstract void draw(Element element, GUI gui);
}
