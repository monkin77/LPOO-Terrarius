package Viewer.Image;

import GUI.GUI;
import Model.elements.Element;

import java.util.ArrayList;
import java.util.List;

public class AnimatedImage extends Image{

    private int currentFrame = 0;
    private int totalFrames;
    private int currentSpeed = 0;
    private int totalSpeed;
    private final List<Image> images = new ArrayList<>();

    public void load(List<String> fileNames, int speed){

        this.totalSpeed = speed;
        this.totalFrames = fileNames.size();

        for (String fname : fileNames){
            Image image = new Image();
            image.load(fname);
            images.add(image);
        }
    }

    public void draw(Element element, GUI gui){

        currentSpeed = (currentSpeed+1) % totalSpeed;

        if (currentSpeed == 0){
            currentFrame = (currentFrame+1) % totalFrames;
        }

        images.get(currentFrame).draw(element, gui);

    }

}
