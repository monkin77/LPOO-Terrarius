package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.blocks.Block;
import Terrarius.Model.Game.elements.blocks.DirtBlock;
import Terrarius.Model.Game.elements.blocks.StoneBlock;
import Terrarius.Model.Game.elements.blocks.WoodBlock;
import Terrarius.Viewer.Image.ColoredImage;

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
