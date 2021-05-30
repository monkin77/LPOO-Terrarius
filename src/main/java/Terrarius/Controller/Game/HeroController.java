package Terrarius.Controller.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.buffs.Buff;
import Terrarius.Model.Game.items.tools.Tool;
import Terrarius.Model.Game.items.BlockPlacer;
import Terrarius.Utils.Dimensions;

import java.util.ArrayList;
import java.util.List;

import static Terrarius.Utils.GameConstants.FRAMES_PER_APPLY_GRAVITY;

public class HeroController {
    private final Arena arena;
    private final List<BuffController> buffList;
    private int fallingVelocity;
    private int gravityFrameCounter;

    public HeroController(Arena arena) {
        this.arena = arena;
        this.fallingVelocity = 1;
        this.gravityFrameCounter = 0;
        this.buffList = new ArrayList<>();
    }

    private Boolean moveHero(Position position) {
        // Can't move outside of arena boundaries
        if (position.getY() < 0 || position.getY() + arena.getHero().getDimensions().getHeight() > arena.getHeight())
            return false;

        if (!arena.collidesWithBlocks(position, arena.getHero().getDimensions())) {

            if(arena.getHero().getToolBar().getActiveItem() != null) {
                Item activeItem = arena.getHero().getToolBar().getActiveItem();
                Position copyPos = activeItem.getPosition(position);
                if(arena.collidesWithBlocks(copyPos, activeItem.getDimensions()))
                    return false;
            }
            arena.getHero().setPosition(position);
        }
        return true;
    }

    private void climbHero(Position position) {
        if (arena.hasAdjacentBlock(arena.getHero().getPosition(), arena.getHero().getDimensions())) {
            moveHero(position);
        }
    }

    public void fallHero() {
        if(!arena.hasAdjacentBlock(arena.getHero().getPosition(), arena.getHero().getDimensions())) {
            Position lasPos = arena.getHero().getPosition();
            for (int i = 0; i < fallingVelocity; ++i)
                moveHero(arena.getHero().getPosition().getDown());

            if (lasPos.equals(arena.getHero().getPosition())) {
                fallingVelocity = 1;
                gravityFrameCounter = 0;
                return;
            }

            gravityFrameCounter++;
            if (gravityFrameCounter % FRAMES_PER_APPLY_GRAVITY == 0)
                fallingVelocity++;
        }
    }

    public void moveHeroLeft() {
        Element.Orientation old_orientation = arena.getHero().getOrientation();
        for (int i = 0; i < arena.getHero().getStats().getSpeed(); ++i)
            arena.getHero().setOrientation(Element.Orientation.LEFT);
            if(!moveHero(arena.getHero().getPosition().getLeft()))
                arena.getHero().setOrientation(old_orientation);

    }

    public void moveHeroRight() {
        Element.Orientation old_orientation = arena.getHero().getOrientation();
        for (int i = 0; i < arena.getHero().getStats().getSpeed(); ++i)
            arena.getHero().setOrientation(Element.Orientation.RIGHT);
            if(!moveHero(arena.getHero().getPosition().getRight()))
                arena.getHero().setOrientation(old_orientation);
    }

    public void moveHeroUp() {
        for (int i = 0; i < arena.getHero().getStats().getSpeed(); ++i)
            climbHero(arena.getHero().getPosition().getUp());
    }

    public void moveHeroDown() {
        for (int i = 0; i < arena.getHero().getStats().getSpeed(); ++i)
            climbHero(arena.getHero().getPosition().getDown());
    }

    public void useItem() {
        Item activeItem = this.arena.getHero().getToolBar().getActiveItem();
        if (activeItem instanceof Tool) this.useTool((Tool) activeItem);
        if (activeItem instanceof Buff) this.useSelectedBuff();
        if (activeItem instanceof BlockPlacer) this.useBlockPlacer();
    }

    private void useBlockPlacer(){
        if (this.arena.getHero().targetWithinRange())
            arena.placeBlock((this.arena.getHero().getTargetPosition()));
    }

    private void useTool(Tool tool) {
        if (this.arena.getHero().targetWithinRange())
            arena.breakBlock(this.arena.getHero().getTargetPosition(), (Tool) arena.getHero().getToolBar().getActiveItem());
    }

    private void useSelectedBuff() {
        Buff buff = (Buff) this.arena.getHero().getToolBar().getActiveItem();
        this.arena.getHero().getToolBar().removeItem(this.arena.getHero().getToolBar().getActiveItemIdx());
        this.arena.getHero().addBuff(buff);

        this.buffList.add(new BuffController(buff, this.arena.getHero()));
    }

    public void updateBuffs(int timeSinceLastUpdate) {
        buffList.removeIf(buffController -> buffController.updateAndCheckDuration(timeSinceLastUpdate));
    }

    private void heroAttack(){
        Item item = arena.getHero().getToolBar().getActiveItem();
        if (this.arena.getHero().targetWithinRange() && item instanceof Tool)
            arena.heroAttack(this.arena.getHero().getTargetPosition(), (Tool) item);
    }

    public void changeHeroSlot(Integer slot) {
        Item item = arena.getHero().getToolBar().getItem(slot);

        if (item != null) {
            Position itemPos = item.getPosition(arena.getHero().getPosition());
            Dimensions itemDimensions = item.getDimensions();

            if (arena.collidesWithBlocks(itemPos, itemDimensions)) return;
        }

            arena.getHero().getToolBar().setActiveItemIdx(slot);
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
                break;
            case PRESS:
                useItem();
                break;
            case CLICK:
                heroAttack();
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
        return arena.getHero().getStats().getHp() > 0;
    }

    public void setTargetPosition(Position targetPosition) {
        this.arena.getHero().setTargetPosition(targetPosition);
    }
}
