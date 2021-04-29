package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Viewer.Image.AnimatedImage;
import Viewer.Image.Image;

import java.util.ArrayList;
import java.util.List;

public class HeroViewer extends ElementViewer{

    public HeroViewer(){

        image = new AnimatedImage();
        image.load("Images/Hero.txt");

    }

    public void draw(Element element, GUI gui){
        image.draw(element, gui);
    }
}
