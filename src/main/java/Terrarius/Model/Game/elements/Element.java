package Terrarius.Model.Game.elements;

import Terrarius.Model.Game.NamedComponent;
import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;

public abstract class Element implements NamedComponent {
    private Position position;
    private Dimensions dimensions;
    private Orientation orientation = Orientation.RIGHT;

    public Element(Position position, Dimensions dimensions) {
        this.position = position;
        this.dimensions = dimensions;
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
    public abstract String getComponentName();
}
