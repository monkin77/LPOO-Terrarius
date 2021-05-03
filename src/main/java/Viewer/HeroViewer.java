package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Viewer.Image.AnimatedImage;

public class HeroViewer extends ElementViewer{

    public HeroViewer(){

        image = new AnimatedImage();
        image.load("Images/Hero.txt");

    }

    @Override
    public void update() {
        image.update();
    }

    public void draw(Element element, GUI gui){
        image.draw(element, gui);
    }
}
