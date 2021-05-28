package Terrarius.Model.Game.arena;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.Block;
import Terrarius.Model.Game.elements.enemies.Enemy;
import Terrarius.Model.Game.elements.enemies.Zombie;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class DefaultArenaBuilder extends ArenaBuilder{
    private final int width;
    private final int height;

    public DefaultArenaBuilder(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected int getHeight() {
        return this.height;
    }

    @Override
    protected int getWidth() {
        return this.width;
    }

    @Override
    protected List<Block> createBlocks() {
        List<Block> blocks = new ArrayList<>();
        try {
            for (int x = 0; x < this.width / 4; x++) {
                blocks.add(new Block(new Position(x * 4, 0), "StoneBlock"));
                blocks.add(new Block(new Position(x * 4, this.height - 4), "StoneBlock"));
            }

            for (int y = 1; y < height / 4; y++) {
                blocks.add(new Block(new Position(0, y * 4), "StoneBlock"));
                blocks.add(new Block(new Position(this.width - 4, y * 4), "StoneBlock"));
            }

            for (int y = height / 4 - 2; y >= height / 4 - 4; y--) {
                blocks.add(new Block(new Position(16, y * 4), "WoodBlock"));
            }
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }

        return blocks;
    }

    @Override
    protected List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        enemies.add(new Zombie(new Position(this.width/4, this.height/4), new Level(1, 10)));
        enemies.add(new Zombie(new Position(this.width/4 + 8, this.height/4 + 4), new Level(1, 10)));
        enemies.add(new Zombie(new Position(4, 4), new Level(1, 0)));
        enemies.add(new Zombie(new Position(this.width - 8, this.height - 12), new Level(5, 10)));

        return enemies;
    }

    @Override
    protected Hero createHero() {
        return new Hero(new Position(this.width/2, this.height/2));
    }
}
