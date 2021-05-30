package Terrarius.Model.Game.elements;

import Terrarius.Model.Game.items.tools.Tool;
import Terrarius.Utils.Dimensions;
import Terrarius.Model.Game.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import static Terrarius.Utils.GameConstants.DEFAULT_BLOCK_DIMENSIONS;

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
