package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Viewer.Image.Image;
import Viewer.Image.StillImage;

import java.util.HashMap;
import java.util.Map;

public abstract class ElementViewer {
    protected Image image;
    protected Map<Element, FrameSpeed> elementFrameSpeedMap = new HashMap<>();
    public abstract void update();
    public abstract void draw(Element element, GUI gui);
}
