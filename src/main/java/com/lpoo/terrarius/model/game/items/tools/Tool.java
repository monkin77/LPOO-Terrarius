package com.lpoo.terrarius.model.game.items.tools;

import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.model.game.items.Item;
import com.lpoo.terrarius.utils.Dimensions;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Tool extends Item {
    private ToolStats stats;
    private int baseFightingPower;
    private int dividerFightingPower;
    private int baseMiningPower;
    private int dividerMiningPower;
    private int miningHardness;

    public Tool(Hero hero, String name) throws FileNotFoundException, URISyntaxException {
        super(hero, name);
    }

    public ToolStats getStats() {
        return stats;
    }

    @Override
    public void updateStats() {
        this.stats = new ToolStats(
                calcStat(baseFightingPower, dividerFightingPower),
                calcStat(baseMiningPower, dividerMiningPower),
                miningHardness
        );
    }

    @Override
    protected void loadItem() throws URISyntaxException, FileNotFoundException {
        URL resource = Tool.class.getResource("/assets/tools/" + getComponentName() + ".txt");
        Scanner scanner = new Scanner(new File(resource.toURI()));

        int height = scanner.nextInt();
        int width = scanner.nextInt();
        this.setDimensions(new Dimensions(height, width));

        this.baseFightingPower = scanner.nextInt();
        this.dividerFightingPower = scanner.nextInt();
        this.baseMiningPower = scanner.nextInt();
        this.dividerMiningPower = scanner.nextInt();
        this.miningHardness = scanner.nextInt();
    }
}
