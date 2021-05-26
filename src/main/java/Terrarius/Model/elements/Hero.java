package Terrarius.Model.elements;

import Terrarius.Model.Dimensions;
import Terrarius.Model.Level;
import Terrarius.Model.Position;
import Terrarius.Model.elements.status.Regeneration;
import Terrarius.Model.elements.status.Strength;
import Terrarius.Model.items.Item;
import Terrarius.Model.items.StatusBar;
import Terrarius.Model.items.Toolbar;

public class Hero extends Element {
    private final StatusBar statusBar;
    private Toolbar toolBar;
    private Position targetPosition;
    private static final Double RANGE = 24.0;

    public Hero(Position position) {
        super(position, new Dimensions(8, 4));
        this.statusBar = new StatusBar(100, 1000, 1, new Level(1, 0));
        this.statusBar.addStatusEffect(new Regeneration(1, 10));
        this.toolBar = new Toolbar(this);
    }

    public StatusBar getStatusBar(){
        return this.statusBar;
    }

    public void updateStatusEffects(){
        this.statusBar.applyStatusEffects();
    }

    public Level getLevel() {
        return this.statusBar.getLevel();
    }

    public int getHealth() {
        return this.statusBar.getHealth();
    }

    public void setLevel(Level level) {
        this.statusBar.setLevel(level);
    }

    public void setHealth(int health) {
        this.statusBar.setHealth(health);
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

    public Boolean targetWithinRange(){ //TODO I forgot what I wanted to call this argument
        Double dist = Math.sqrt(
                Math.pow((this.getPosition().getX() + this.getDimensions().getWidth()/2.0) - targetPosition.getX(), 2) +
                Math.pow((this.getPosition().getY() + this.getDimensions().getHeight()/2.0) - targetPosition.getY(), 2));

        if (dist <= RANGE) return true;
        else return false;
    }
}
