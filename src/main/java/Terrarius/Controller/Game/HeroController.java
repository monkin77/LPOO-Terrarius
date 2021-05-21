package Terrarius.Controller.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.items.Item;

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
                break;
            case SLOT1:
                changeHeroSlot(1);
                break;
            case SLOT2:
                changeHeroSlot(2);
                break;
            case SLOT3:
                changeHeroSlot(3);
                break;
            case SLOT4:
                changeHeroSlot(4);
                break;
            case SLOT5:
                changeHeroSlot(5);
                break;
            case SLOT6:
                changeHeroSlot(6);
                break;
            case SLOT7:
                changeHeroSlot(7);
                break;
            case SLOT8:
                changeHeroSlot(8);
                break;
            case SLOT9:
                changeHeroSlot(9);
                break;
        }
    }

    public boolean isHeroAlive() {
        return arena.getHero().getHealth() > 0;
    }
}
