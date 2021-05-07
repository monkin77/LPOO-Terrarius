package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Model.elements.blocks.Block;
import Model.elements.blocks.DirtBlock;
import Model.elements.blocks.StoneBlock;
import Model.elements.blocks.WoodBlock;
import Viewer.Image.ColoredImage;
import Viewer.Image.StillImage;

public class BlockViewer extends ElementViewer{

    public BlockViewer(Block block){

        if(block.getClass().equals(DirtBlock.class)){
            image = new StillImage();
            image.load("Images/Dirt.txt");
        }
        else if(block.getClass().equals(StoneBlock.class)){
            image = new StillImage();
            image.load("Images/Stone.txt");
        }
        else if(block.getClass().equals(WoodBlock.class)){
            image = new ColoredImage();
            image.load("Images/ColoredWood.txt");
        }
    }

    @Override
    public void update() {
        image.update();
    }

    @Override
    public void draw(Element element, GUI gui) {
        this.image.draw(element.getPosition(), element.getOrientation(), gui);
    }
}
