package Model.elements;

import Model.Dimensions;
import Model.Level;
import Model.Position;
import Model.items.Item;
import Model.items.Toolbar;

public class Hero extends Element {
    private Level level;
    private int health;
    private Toolbar toolBar;
    private Position targetPosition;

    public Hero(Position position) {
        super(position, new Dimensions(8, 4));
        this.level = new Level(1, 0);
        this.health = 100;
        this.toolBar = new Toolbar();
    }

    public Level getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Toolbar getToolBar() {
        return toolBar;
    }

    public void setToolBar(Toolbar toolBar) {
        this.toolBar = toolBar;
    }

    /**
     * Adds an item to the first free slot. If full, the item is not added
     */
    public void addItemFreeSlot(Item item) {
        Integer key = this.toolBar.findFreeSlot();
        if (key != -1) this.toolBar.setItem(key, item);
    }

    /**
     * Adds an item to a specific toolbar slot. If there is already an item on that position, replace it.
     */
    public void addItem(Integer toolBarPosition, Item item) {
        this.toolBar.setItem(toolBarPosition, item);
    }

    public void removeItem(Integer toolBarPosition) {
        this.toolBar.removeItem(toolBarPosition);
    }

    public Dimensions getDimensionsWithItem() {
        if(toolBar.getActiveItemIdx() == 0) return this.getDimensions();

        Dimensions totalDimensions = new Dimensions(this.getDimensions());
        totalDimensions.incrementWidth(toolBar.getActiveItem().getDimensions().getWidth());
        return totalDimensions;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }
}
