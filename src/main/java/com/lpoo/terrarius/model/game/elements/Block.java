package com.lpoo.terrarius.model.game.elements;

import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.items.tools.Tool;
import com.lpoo.terrarius.utils.Dimensions;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import static com.lpoo.terrarius.utils.GameConstants.DEFAULT_BLOCK_DIMENSIONS;

public class Block extends Element {
    private int hp;
    private int hardness;

    public Block(Position position, String name) throws FileNotFoundException, URISyntaxException {
        super(position, name);
        loadBlock();
    }

    public int getHP() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHardness() {
        return hardness;
    }

    private void loadBlock() throws URISyntaxException, FileNotFoundException {
        URL resource = Tool.class.getResource("/assets/blocks/" + getComponentName() + ".txt");
        Scanner scanner = new Scanner(new File(resource.toURI()));

        this.hp = scanner.nextInt();
        this.hardness = scanner.nextInt();
    }

    @Override
    protected void loadElement() {
        this.setDimensions(new Dimensions(DEFAULT_BLOCK_DIMENSIONS));
    }
}
