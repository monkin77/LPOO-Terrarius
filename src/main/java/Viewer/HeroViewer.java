package Viewer;

import GUI.GUI;
import Model.elements.Element;
import Viewer.Image.AnimatedImage;
import Viewer.Image.Image;

import java.util.ArrayList;
import java.util.List;

public class HeroViewer extends ElementViewer{

    Image image;

    public HeroViewer(){

        AnimatedImage animatedImage = new AnimatedImage();
        List <String> fnames = new ArrayList<>();
        fnames.add("Images/Hero1.txt");
        fnames.add("Images/Hero2.txt");
        animatedImage.load(fnames, 1);
        image = animatedImage;

    }

    public void draw(Element element, GUI gui){
        image.draw(element, gui);
    }
}
