package Viewer.Image;

import GUI.GUI;
import Model.elements.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimatedImage extends Image {

    private int currentFrame = 0;
    private int totalFrames;
    private int currentSpeed = 0;
    private int totalSpeed;
    private final List<StillImage> images = new ArrayList<>();

    public void load(String fname){

        URL resource = getClass().getClassLoader().getResource(fname);

        try {
            File imageFile = new File(resource.toURI());

            Scanner imageScanner = new Scanner(imageFile);

            this.totalFrames = imageScanner.nextInt();
            this.totalSpeed = imageScanner.nextInt();

            imageScanner.nextLine(); //clears the /n

            for(int i = 0; i < totalFrames; i++){
                StillImage image = new StillImage();
                String nFname = imageScanner.nextLine();
                image.load(nFname);
                images.add(image);
            }

        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        currentSpeed = (currentSpeed+1) % totalSpeed;

        if (currentSpeed == 0){
            currentFrame = (currentFrame+1) % totalFrames;
        }
    }

    public void draw(Element element, GUI gui){

        images.get(currentFrame).draw(element, gui);

    }

}
