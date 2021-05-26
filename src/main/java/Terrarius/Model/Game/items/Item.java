package Terrarius.Model.Game.items;

import Terrarius.Model.Game.NamedComponent;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.elements.hero.Hero;

public abstract class Item implements NamedComponent {
    private final Hero hero;
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

    /**
     * Calculate item position relative to a certain position
     */
    public Position getPosition(Position possiblePosition) {
        Position itemPos = new Position(possiblePosition);

        itemPos.incrementY(hero.getDimensions().getHeight()/2 - 2 - dimensions.getHeight());
        itemPos.incrementX( hero.getOrientation() == Element.Orientation.RIGHT ? hero.getDimensions().getWidth() : - dimensions.getWidth() );
        return itemPos;
    }

    // Item stats should be updated when hero level/stats are increased
    public abstract void updateStats();

    @Override
    public abstract String getComponentName();
}
