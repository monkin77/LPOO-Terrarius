package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.elements.Element;

public interface ElementViewer<T extends Element> {
    void draw(T element, GUI gui);
}
