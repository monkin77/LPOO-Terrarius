package com.lpoo.terrarius.viewer.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.elements.Block;
import com.lpoo.terrarius.viewer.image.ColoredImage;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class BlockViewer extends ElementViewer<Block, ColoredImage> {
    public BlockViewer(Block block){
        super(block.getComponentName());
    }

    @Override
    public void draw(Block block, GUI gui) {
        image.draw(block.getPosition(), block.getOrientation(), gui);
    }

    @Override
    protected ColoredImage createImage() {
        ColoredImage image = new ColoredImage();

        try {
            image.load("Images/Blocks/" + this.getComponentName() + ".txt");
        } catch (FileNotFoundException | URISyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        return image;
    }
}
