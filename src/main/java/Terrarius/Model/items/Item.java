package Terrarius.Model.items;

import Terrarius.Model.Dimensions;
import Terrarius.Model.Position;
import Terrarius.Model.elements.Element;
import Terrarius.Model.elements.Hero;

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
        itemPos.incrementY(hero.getDimensions().getHeight()/2 - 2 - dimensions.getHeight());
        itemPos.incrementX( hero.getOrientation() == Element.Orientation.RIGHT ? hero.getDimensions().getWidth() - 1 : - dimensions.getWidth() );
        return itemPos;
    }

    /**
     * Calculate item position when the hero tries to move to position possiblePosition
     * @param possiblePosition
     * @return
     */
    public Position getPosition(Position possiblePosition) {
        Position itemPos = new Position(possiblePosition);
        itemPos.incrementY(hero.getDimensions().getHeight()/2 - 2 - dimensions.getHeight());
        itemPos.incrementX( hero.getOrientation() == Element.Orientation.RIGHT ? hero.getDimensions().getWidth() - 1 : - dimensions.getWidth() );
        return itemPos;
    }


    // Item stats should be updated when hero level/stats are increased
    public abstract void updateStats();
}
