package Controller;

import GUI.GUI;
import Model.Position;
import Model.arena.Arena;
import Model.elements.Element;

public class HeroController {
    private final Arena arena;

    public HeroController(Arena arena) {
        this.arena = arena;
    }

    private void moveHero(Position position) {
        // Can't move outside of arena boundaries
        if (position.getX() < 0 || position.getX() > arena.getWidth()
            || position.getY() < 0 || position.getY() > arena.getHeight())
            return;

        if (!arena.collides(position, arena.getHero().getDimensions())) {
            arena.getHero().setPosition(position);
        }
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
        moveHero(arena.getHero().getPosition().getUp());
    }

    public void moveHeroDown() {
        moveHero(arena.getHero().getPosition().getDown());
    }

    public void doAction(GUI.ACTION action) {
        switch (action) {
            case UP:
                // TO DO: JUMPING
                break;
            case LEFT:
                moveHeroLeft();
                break;
            case RIGHT:
                moveHeroRight();
                break;
            case CLICK:
                // TO DO: USE ITEM
                break;
        }
    }

    public boolean isHeroAlive() {
        return arena.getHero().getHealth() > 0;
    }
}
