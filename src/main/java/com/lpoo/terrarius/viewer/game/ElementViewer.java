package com.lpoo.terrarius.viewer.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.elements.Element;
import com.lpoo.terrarius.viewer.image.Image;

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

    public U getImage() {
        return image;
    }
}
