package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Model.elements.blocks.Block;
import Model.elements.blocks.DirtBlock;
import Model.elements.blocks.StoneBlock;
import Model.elements.blocks.WoodBlock;
import Viewer.Image.ColoredImage;

public class BlockViewer extends ElementViewer{

    public BlockViewer(Block block){

        setImage(new ColoredImage());
        if(block.getClass().equals(DirtBlock.class)){
            getImage().load("Images/Blocks/Dirt.txt");
        }
        else if(block.getClass().equals(StoneBlock.class)){
            getImage().load("Images/Blocks/Stone.txt");
        }
        else if(block.getClass().equals(WoodBlock.class)){
            getImage().load("Images/Blocks/ColoredWood.txt");
        }
    }

    @Override
    public void update() {
        getImage().update();
    }

    @Override
    public void draw(Element element, GUI gui) {
        getImage().draw(element.getPosition(), element.getOrientation(), gui);
    }
}
