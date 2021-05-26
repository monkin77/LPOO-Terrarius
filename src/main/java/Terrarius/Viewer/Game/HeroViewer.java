package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Viewer.Image.AnimatedImage;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class HeroViewer extends ElementViewer{

    public HeroViewer(){
        setImage(new AnimatedImage());
        try {
            getImage().load("Images/Hero/Hero.txt");
        } catch (FileNotFoundException | URISyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        getImage().update();
    }

    @Override
    public void draw(Element element, GUI gui){
        getImage().draw(element.getPosition(), element.getOrientation(), gui);
    }
}
