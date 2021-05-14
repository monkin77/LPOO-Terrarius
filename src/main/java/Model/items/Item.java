package Model.items;

import Model.Dimensions;
import Model.Position;
import Model.elements.Element;
import Model.elements.Hero;

public abstract class Item {
    private Hero hero;
    private Dimensions dimensions;

    public Item(Hero hero, Dimensions dimensions) {
        this.hero = hero;
        this.dimensions = dimensions;
        updateStats();
    }

    public Hero getHero() {
        return hero;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public Position getPosition() {
        Position itemPos = new Position(hero.getPosition());
        itemPos.setY( itemPos.getY() + (hero.getDimensions().getHeight()/2 - 2) - dimensions.getHeight());
        itemPos.setX(itemPos.getX() + ( hero.getOrientation() == Element.Orientation.RIGHT ? hero.getDimensions().getWidth() - 1 : - dimensions.getWidth() ) );
        return itemPos;
    }

    // Item stats should be updated when hero level/stats are increased
    public abstract void updateStats();
}
