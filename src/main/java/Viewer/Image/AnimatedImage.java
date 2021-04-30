package Viewer.Image;

import GUI.GUI;
import Model.elements.Element;
import Viewer.FrameSpeed;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimatedImage extends Image {

    private FrameSpeed frameSpeed = new FrameSpeed();

    private final List<StillImage> images = new ArrayList<>();

    public void load(String fname){

        URL resource = getClass().getClassLoader().getResource(fname);

        try {
            File imageFile = new File(resource.toURI());

            Scanner imageScanner = new Scanner(imageFile);

            int totalFrames = imageScanner.nextInt();
            int totalSpeed = imageScanner.nextInt();

            this.frameSpeed.setTotalSpeed(totalSpeed);
            this.frameSpeed.setTotalFrames(totalFrames);

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
        frameSpeed.update();
    }

    @Override
    public void reset() {
        frameSpeed.reset();
    }

    public void draw(Element element, GUI gui){

        images.get(frameSpeed.getCurrentFrame()).draw(element, gui);

    }

    public FrameSpeed getFrameSpeed() {
        return frameSpeed;
    }

    public void setFrameSpeed(FrameSpeed frameSpeed) {
        this.frameSpeed = frameSpeed;
    }
}
