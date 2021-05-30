package Terrarius.Model.Game.elements;

import Terrarius.Model.Game.NamedComponent;
import Terrarius.Model.Game.Position;
import Terrarius.Utils.Dimensions;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public abstract class Element implements NamedComponent {
    private Position position;
    private Dimensions dimensions;
    private final String name;
    private Orientation orientation = Orientation.RIGHT;

    public Element(Position position, String name) throws FileNotFoundException, URISyntaxException {
        this.position = position;
        this.name = name;
        loadElement();
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation(){ return orientation;}

    public void setPosition(Position position) {
        this.position = position;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public enum Orientation {LEFT, RIGHT}

    @Override
    public String getComponentName() {
        return this.name;
    }

    protected abstract void loadElement() throws FileNotFoundException, URISyntaxException;
}
