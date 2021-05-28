package Terrarius.Model.Game.items.buffs;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.Item;
import Terrarius.Model.Game.items.tools.Tool;
import Terrarius.Utils.Dimensions;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Buff extends Item {
    private BuffStats stats;

    private int baseDuration;
    private int dividerDuration;
    private int baseHP;
    private int dividerHP;
    private int basePower;
    private int dividerPower;
    private int baseSpeed;
    private int dividerSpeed;
    private int baseRange;
    private int dividerRange;

    public Buff(Hero hero, String name) throws FileNotFoundException, URISyntaxException {
        super(hero, name);
    }

    public BuffStats getStats() {
        return stats;
    }

    public void setStats(BuffStats stats) {
        this.stats = stats;
    }

    @Override
    public void updateStats() {
        this.stats = new BuffStats(
                calcStat(baseDuration, dividerDuration),
                calcStat(baseHP, dividerHP),
                calcStat(basePower, dividerPower),
                calcStat(baseSpeed, dividerSpeed),
                calcStat(baseRange, dividerRange)
        );
    }

    @Override
    protected void loadItem() throws URISyntaxException, FileNotFoundException {
        URL resource = Tool.class.getResource("/assets/buffs/" + getComponentName() + ".txt");
        Scanner scanner = new Scanner(new File(resource.toURI()));

        int height = scanner.nextInt();
        int width = scanner.nextInt();
        this.setDimensions(new Dimensions(height, width));

        this.baseDuration = scanner.nextInt();
        this.dividerDuration = scanner.nextInt();
        this.baseHP = scanner.nextInt();
        this.dividerHP = scanner.nextInt();
        this.basePower = scanner.nextInt();
        this.dividerPower = scanner.nextInt();
        this.baseSpeed = scanner.nextInt();
        this.dividerSpeed = scanner.nextInt();
        this.baseRange = scanner.nextInt();
        this.dividerRange = scanner.nextInt();

    }
}
