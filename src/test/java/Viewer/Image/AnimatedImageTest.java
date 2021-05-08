package Viewer.Image;

import GUI.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class AnimatedImageTest {
    private AnimatedImage animatedImage;
    private GUI gui;

    @BeforeEach
    public void setup() {
        animatedImage = new AnimatedImage();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void load() throws FileNotFoundException, URISyntaxException {
        Scanner imageScanner = Mockito.mock(Scanner.class);
        Mockito.when(imageScanner.nextInt()).thenReturn(10);
        Mockito.when(imageScanner.nextLine()).thenReturn("");

        animatedImage = Mockito.spy(animatedImage);
        Mockito.doReturn(imageScanner).when(animatedImage).getScannerFromFile(Mockito.anyString());
        Mockito.doNothing().when(animatedImage).addNextImage(imageScanner);

        animatedImage.load("Test");

        Mockito.verify(imageScanner, Mockito.times(2)).nextInt();
        Mockito.verify(imageScanner, Mockito.times(1)).nextLine();
        Mockito.verify(animatedImage, Mockito.times(10)).addNextImage(imageScanner);
    }
}
