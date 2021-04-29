package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Model.elements.blocks.Block;
import Model.elements.blocks.DirtBlock;
import Model.elements.blocks.StoneBlock;
import Model.elements.blocks.WoodBlock;
import Viewer.Image.Image;
import Viewer.Image.StillImage;

public class BlockViewer extends ElementViewer{

    public BlockViewer(Block block){

        image = new StillImage();

        if(block.getClass().equals(DirtBlock.class)){
            image.load("Images/Dirt.txt");
        }
        else if(block.getClass().equals(StoneBlock.class)){
            image.load("Images/Stone.txt");
        }
        else if(block.getClass().equals(WoodBlock.class)){
            image.load("Images/Wood.txt");
        }
    }

    @Override
    public void draw(Element element, GUI gui) {
        this.image.draw(element, gui);
    }
}
