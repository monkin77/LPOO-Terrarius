package Terrarius.Viewer;

import Terrarius.GUI.GUI;
import Terrarius.Model.elements.Element;
import Terrarius.Viewer.Image.AnimatedImage;

public class HeroViewer extends ElementViewer{

    public HeroViewer(){
        setImage(new AnimatedImage());
        getImage().load("Images/Hero/Hero.txt");
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
