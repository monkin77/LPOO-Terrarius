package Terrarius.Model.arena;

import Terrarius.Model.elements.Hero;
import Terrarius.Model.elements.blocks.Block;
import Terrarius.Model.elements.enemies.Enemy;

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
