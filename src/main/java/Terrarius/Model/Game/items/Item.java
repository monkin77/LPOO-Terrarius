package Terrarius.Model.Game.items;

import Terrarius.Model.Game.NamedComponent;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Utils.Dimensions;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public abstract class Item implements NamedComponent {
    private final Hero hero;
    private Dimensions dimensions;
    private final String name;

    public Item(Hero hero, String name) throws FileNotFoundException, URISyntaxException {
        this.hero = hero;
        this.name = name;
        loadItem();
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

    public Position getPosition(Position possiblePosition) {
        Position itemPos = new Position(possiblePosition);

        itemPos.incrementY(hero.getDimensions().getHeight()/2 - 2 - dimensions.getHeight());
        itemPos.incrementX( hero.getOrientation() == Element.Orientation.RIGHT ? hero.getDimensions().getWidth() : - dimensions.getWidth() );
        return itemPos;
    }

    protected int calcStat(int base, int divider) {
        if (divider == 0) return base;
        return base + hero.getStats().getCurrentLevel() / divider;
    }

    @Override
    public String getComponentName() {
        return this.name;
    }

    protected abstract void loadItem() throws URISyntaxException, FileNotFoundException;

    public abstract void updateStats();
}
