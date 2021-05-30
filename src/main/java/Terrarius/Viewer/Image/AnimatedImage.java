package Terrarius.Viewer.Image;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Viewer.FrameHandler;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimatedImage extends Image {

    private FrameHandler frameHandler = new FrameHandler();
    private final List<ColoredImage> images = new ArrayList<>();

    @Override
    public void load(String fname) throws FileNotFoundException, URISyntaxException {
        Scanner imageScanner = getScannerFromFile(fname);

        int totalFrames = imageScanner.nextInt();
        int totalSpeed = imageScanner.nextInt();

        this.frameHandler.setTotalFPI(totalSpeed);
        this.frameHandler.setTotalImages(totalFrames);

        imageScanner.nextLine();

        for(int i = 0; i < totalFrames; i++)
            addNextImage(imageScanner);
    }

    public void update() {
        frameHandler.update();
    }

    @Override
    public void draw(Position position, Element.Orientation orientation, GUI gui){
        images.get(frameHandler.getCurrentImage()).draw(position, orientation, gui);
    }

    public FrameHandler getFrameSpeed() {
        return frameHandler;
    }

    public void setFrameSpeed(FrameHandler frameHandler) {
        this.frameHandler = frameHandler;
    }

    protected void addNextImage(Scanner imageScanner) throws FileNotFoundException, URISyntaxException {
        ColoredImage image = new ColoredImage();
        String nFname = imageScanner.nextLine();
        image.load(nFname);
        images.add(image);
    }
}
