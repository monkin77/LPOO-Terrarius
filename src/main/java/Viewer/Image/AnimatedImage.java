package Viewer.Image;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Viewer.FrameHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimatedImage extends Image {

    private FrameHandler frameHandler = new FrameHandler();

    private final List<StillImage> images = new ArrayList<>();

    public void load(String fname){

        URL resource = getClass().getClassLoader().getResource(fname);

        try {
            File imageFile = new File(resource.toURI());

            Scanner imageScanner = new Scanner(imageFile);

            int totalFrames = imageScanner.nextInt();
            int totalSpeed = imageScanner.nextInt();

            this.frameHandler.setTotalFPI(totalSpeed);
            this.frameHandler.setTotalImages(totalFrames);

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
        frameHandler.update();
    }

    @Override
    public void reset() {
        frameHandler.reset();
    }

    public void draw(Position position, Element.Orientation orientation, GUI gui){

        images.get(frameHandler.getCurrentImage()).draw(position, orientation, gui);

    }

    public FrameHandler getFrameSpeed() {
        return frameHandler;
    }

    public void setFrameSpeed(FrameHandler frameHandler) {
        this.frameHandler = frameHandler;
    }
}
