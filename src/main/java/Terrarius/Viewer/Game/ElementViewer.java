package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Viewer.Image.Image;

public abstract class ElementViewer<T extends Element, U extends Image> {
    U image;
    String componentName;

    public ElementViewer(String componentName) {
        this.componentName = componentName;
        this.image = createImage();
    }

    public ElementViewer() {
        this.image = createImage();
    }

    protected String getComponentName() {
        return componentName;
    }

    protected abstract U createImage();
    public abstract void draw(T component, GUI gui);

    public void setImage(U image) {
        this.image = image;
    }
}
