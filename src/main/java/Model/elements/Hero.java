package Model.elements;

import Model.Dimensions;
import Model.Level;
import Model.Position;
import Model.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Hero extends Element {
    private Level level;
    private int health;
    private Map<Integer, Item> toolBar = new HashMap<>();

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

    public Map<Integer, Item> getToolBar() {
        return toolBar;
    }

    public void addItem(Integer toolBarPosition, Item item) {
        // Check if there is already an item in the slot
        this.toolBar.put(toolBarPosition, item);
    }

    public void removeItem(Integer toolBarPosition) {
        this.toolBar.remove(toolBarPosition);
    }

    // TO DO: ITEMS LOGIC (maybe limit to X items)
}
