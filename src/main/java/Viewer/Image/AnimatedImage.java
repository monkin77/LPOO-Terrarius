package Viewer.Image;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Viewer.FrameHandler;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimatedImage extends Image {

    private FrameHandler frameHandler;
    private final List<ColoredImage> images;

    public AnimatedImage() {
        frameHandler = new FrameHandler();
        images = new ArrayList<>();
    }

    public void load(String fname) {
        try {
            Scanner imageScanner = getScannerFromFile(fname);

            int totalFrames = imageScanner.nextInt();
            int totalSpeed = imageScanner.nextInt();

            this.frameHandler.setTotalFPI(totalSpeed);
            this.frameHandler.setTotalImages(totalFrames);

            imageScanner.nextLine(); //clears the /n

            for(int i = 0; i < totalFrames; i++)
                addNextImage(imageScanner);

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

    protected void addNextImage(Scanner imageScanner) {
        ColoredImage image = new ColoredImage();
        String nFname = imageScanner.nextLine();
        image.load(nFname);
        images.add(image);
    }
}
