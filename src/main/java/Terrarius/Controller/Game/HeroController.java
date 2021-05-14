package Terrarius.Controller.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Position;
import Terrarius.Model.arena.Arena;
import Terrarius.Model.elements.Element;
import Terrarius.Model.items.Item;

public class HeroController {
    private final Arena arena;

    public HeroController(Arena arena) {
        this.arena = arena;
    }

    private void moveHero(Position position) {
        // Can't move outside of arena boundaries
        if (position.getX() < 0 || position.getX() + arena.getHero().getDimensions().getWidth() > arena.getWidth()
            || position.getY() < 0 || position.getY() + arena.getHero().getDimensions().getHeight() > arena.getHeight())
            return;

        if (!arena.collides(position, arena.getHero().getDimensions())) {

            if(arena.getHero().getToolBar().getActiveItem() != null) {
                Item activeItem = arena.getHero().getToolBar().getActiveItem();
                Position copyPos = activeItem.getPosition(position);
                if(arena.collides(copyPos, activeItem.getDimensions()))
                    return;
            }

            arena.getHero().setPosition(position);
        }
    }

    private void climbHero(Position position) {
        if (arena.hasAdjacentBlock(arena.getHero().getPosition(), arena.getHero().getDimensions())) {
            moveHero(position);
        }
    }

    public void fallHero() {
        if(!arena.hasAdjacentBlock(arena.getHero().getPosition(), arena.getHero().getDimensions()))
            moveHero(arena.getHero().getPosition().getDown());
    }

    public void moveHeroLeft() {
        moveHero(arena.getHero().getPosition().getLeft());
        arena.getHero().setOrientation(Element.Orientation.LEFT);
    }

    public void moveHeroRight() {
        moveHero(arena.getHero().getPosition().getRight());
        arena.getHero().setOrientation(Element.Orientation.RIGHT);
    }

    public void moveHeroUp() {
        climbHero(arena.getHero().getPosition().getUp());
    }

    public void moveHeroDown() {
        climbHero(arena.getHero().getPosition().getDown());
    }

    public void changeHeroSlot(Integer slot) {
        this.arena.getHero().getToolBar().setActiveItemIdx(slot);
    }

    public void doAction(GUI.ACTION action) {
        switch (action) {
            case UP:
                moveHeroUp();
                break;
            case LEFT:
                moveHeroLeft();
                break;
            case RIGHT:
                moveHeroRight();
                break;
            case DOWN:
                moveHeroDown();
            case CLICK:
                // TODO: USE ITEM
                break;
            case SLOT0:
                changeHeroSlot(0);
                System.out.println("0 Pressed!");
                break;
            case SLOT1:
                changeHeroSlot(1);
                System.out.println("1 Pressed!");
                break;
        }
    }

    public boolean isHeroAlive() {
        return arena.getHero().getHealth() > 0;
    }
}
