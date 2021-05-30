package Terrarius.Model.Game.elements.enemies;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Utils.Dimensions;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class Enemy extends Element {
    private final EnemyStats stats;

    private int baseHP;
    private int multiplierHP;
    private int basePower;
    private int multiplierPower;
    private int viewDist;

    public Enemy(Position position, Level level, String name) throws FileNotFoundException, URISyntaxException {
        super(position, name);

        this.stats = new EnemyStats(
            calcStat(baseHP, multiplierHP, level),
            calcStat(basePower, multiplierPower, level),
            viewDist,
            level
        );
    }

    public EnemyStats getStats() {
        return stats;
    }

    public void setHP(int hp) {
        this.stats.setHp(hp);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Enemy enemy2 = (Enemy) obj;
        return this.getComponentName().equals(enemy2.getComponentName())
                && this.stats.equals(enemy2.getStats())
                && getPosition().equals(enemy2.getPosition())
                && getDimensions().equals(enemy2.getDimensions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(stats.getLevel(), stats.getPower(), stats.getViewDistance(),
                getComponentName(), getDimensions());
    }

    private int calcStat(int base, int multiplier, Level level) {
        return base + multiplier * level.getNumLevel();
    }

    @Override
    protected void loadElement() throws FileNotFoundException, URISyntaxException {
        URL resource = Enemy.class.getResource("/assets/enemies/" + getComponentName() + ".txt");
        Scanner scanner = new Scanner(new File(resource.toURI()));

        int height = scanner.nextInt();
        int width = scanner.nextInt();
        this.setDimensions(new Dimensions(height, width));

        this.baseHP = scanner.nextInt();
        this.multiplierHP = scanner.nextInt();
        this.basePower = scanner.nextInt();
        this.multiplierPower = scanner.nextInt();
        this.viewDist = scanner.nextInt();
    }
}
