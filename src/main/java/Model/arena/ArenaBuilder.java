package Model.arena;

import Model.elements.Hero;
import Model.elements.blocks.Block;
import Model.elements.enemies.Enemy;

import java.util.List;

public abstract class ArenaBuilder {
    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());

        arena.setHero(createHero());
        arena.setEnemies(createEnemies());
        arena.setBlocks(createBlocks());

        return arena;
    }

    protected abstract int getHeight();

    protected abstract int getWidth();

    protected abstract List<Block> createBlocks();

    protected abstract List<Enemy> createEnemies();

    protected abstract Hero createHero();
}
