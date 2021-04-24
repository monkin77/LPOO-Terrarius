package Model.elements.blocks;

import Model.Position;
import Model.elements.Element;

public abstract class Block extends Element {
    private int hp;

    public Block(Position position) {
        super(position);
        this.hp = initHP();
    }

    public int getHP() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    // Functions for block initial stats
    abstract int initHP();
    abstract int getHardness();
}
