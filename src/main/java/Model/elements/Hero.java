package Model.elements;

import Model.Dimensions;
import Model.Level;
import Model.Position;
import Model.items.Item;
import Model.items.Toolbar;

public class Hero extends Element {
    private Level level;
    private int health;
    private Toolbar toolBar = new Toolbar();

    public Hero(Position position) {
        super(position, new Dimensions(8, 4));
        this.level = new Level(1, 0);
        this.health = 100;
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

    /**
     * Adds an item to the first free slot. If full, the item is not added
     * @param item
     */
    public void addItemFreeSlot(Item item) {
        Integer key = this.toolBar.findFreeSlot();
        if(key == -1)
            return;
        this.toolBar.setItem(key, item);
    }

    /**
     * Adds an item to a specific toolbar slot. If there is already an item on that position, replace it.
     * @param toolBarPosition
     * @param item
     */
    public void addItem(Integer toolBarPosition, Item item) {
        // Check if there is already an item in the slot
        this.toolBar.setItem(toolBarPosition, item);
    }

    public void removeItem(Integer toolBarPosition) {
        this.toolBar.removeItem(toolBarPosition);
    }

    // TO DO: ITEMS LOGIC (maybe limit to X items)
}
