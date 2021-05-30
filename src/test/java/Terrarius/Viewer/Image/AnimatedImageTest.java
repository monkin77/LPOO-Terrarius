package Terrarius.Viewer.Image;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Viewer.FrameHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class AnimatedImageTest {
    private AnimatedImage animatedImage;
    private GUI gui;
    private Scanner imageScanner;
    private FrameHandler frameHandler;

    @BeforeEach
    public void setup() throws FileNotFoundException, URISyntaxException {
        gui = Mockito.mock(GUI.class);
        Mockito.doNothing().when(gui).drawCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar(),
                                                    Mockito.anyString(), Mockito.anyString());

        animatedImage = new AnimatedImage();

        imageScanner = Mockito.mock(Scanner.class);
        Mockito.when(imageScanner.nextInt()).thenReturn(10);
        Mockito.when(imageScanner.nextLine()).thenReturn("");

        animatedImage = Mockito.spy(animatedImage);
        Mockito.doReturn(imageScanner).when(animatedImage).getScannerFromFile(Mockito.anyString());

        frameHandler = Mockito.mock(FrameHandler.class);
        Mockito.when(frameHandler.getCurrentImage()).thenReturn(0);
        animatedImage.setFrameSpeed(frameHandler);
    }

    @Test
    public void load() throws FileNotFoundException, URISyntaxException {
        Mockito.doNothing().when(animatedImage).addNextImage(imageScanner);
        animatedImage.load("Test");

        Mockito.verify(imageScanner, Mockito.times(2)).nextInt();
        Mockito.verify(imageScanner, Mockito.times(1)).nextLine();
        Mockito.verify(animatedImage, Mockito.times(10)).addNextImage(imageScanner);
    }

    @Test
    public void draw() throws FileNotFoundException, URISyntaxException {
        ColoredImage image = Mockito.mock(ColoredImage.class);
        animatedImage.addImage(image);
        Position pos = new Position(5, 5);

        animatedImage.draw(pos, Element.Orientation.RIGHT, gui);

        Mockito.verify(image, Mockito.times(1)).draw(pos, Element.Orientation.RIGHT, gui);
    }
}
