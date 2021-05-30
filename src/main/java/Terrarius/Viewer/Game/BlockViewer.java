package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.elements.Block;
import Terrarius.Viewer.Image.ColoredImage;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class BlockViewer implements ElementViewer<Block> {
    ColoredImage image;

    public BlockViewer(Block block){
        image = new ColoredImage();

        try {
            image.load("Images/Blocks/" + block.getComponentName() + ".txt");
        } catch (FileNotFoundException | URISyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Block block, GUI gui) {
        image.draw(block.getPosition(), block.getOrientation(), gui);
    }
}
