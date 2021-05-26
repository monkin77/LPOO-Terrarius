package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.blocks.Block;
import Terrarius.Viewer.Image.ColoredImage;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class BlockViewer extends ElementViewer{

    public BlockViewer(Block block){
        setImage(new ColoredImage());

        try {
            getImage().load("Images/Blocks/" + block.getComponentName() + ".txt");
        } catch (FileNotFoundException | URISyntaxException | NullPointerException e) {
            e.printStackTrace();
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
