package Terrarius.Model.Game.elements.blocks;

import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;

public abstract class Block extends Element {
    private int hp;
    private final String name;

    public Block(Position position, Dimensions dimensions, String name) {
        super(position, dimensions);
        this.hp = initHP();
        this.name = name;
    }

    public int getHP() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    // Functions for block initial stats
    abstract int initHP();
    public abstract int getHardness();

    public String getName(){ return this.name;}
}
