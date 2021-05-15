package Viewer.Image;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class StillImageTest {
    private StillImage stillImage;
    private GUI gui;

    @BeforeEach
    public void setup() {
        stillImage = new StillImage();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void load() throws FileNotFoundException, URISyntaxException {
        Scanner imageScanner = Mockito.mock(Scanner.class);
        Mockito.when(imageScanner.nextInt()).thenReturn(100);

        stillImage = Mockito.spy(stillImage);
        Mockito.doReturn(imageScanner).when(stillImage).getScannerFromFile(Mockito.anyString());
        Mockito.doNothing().when(stillImage).loadAspect(imageScanner, 100, 100);

        stillImage.load("Test");
        Mockito.verify(stillImage, Mockito.times(1)).getScannerFromFile("Test");
        Mockito.verify(stillImage, Mockito.times(1)).loadAspect(imageScanner, 100, 100);
    }

    @Test
    public void loadNoFile() {
        Assertions.assertThrows(NullPointerException.class, () -> stillImage.load("Test.txt"));
    }

    @Test
    public void loadAspect() {
        Scanner imageScanner = Mockito.mock(Scanner.class);
        Mockito.when(imageScanner.nextLine()).thenReturn("");

        stillImage.loadAspect(imageScanner, 10, 10);
        Mockito.verify(imageScanner, Mockito.times(11)).nextLine();
    }

    @Test
    public void draw() {
        stillImage.load("Images/Items/AxeIcon.txt");
        stillImage.draw(new Position(10, 10), Element.Orientation.RIGHT, gui);

        Mockito.verify(gui, Mockito.times(9)).
                drawCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());

        stillImage.draw(new Position(10, 10), Element.Orientation.LEFT, gui);

        Mockito.verify(gui, Mockito.times(18)).
                drawCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());
    }
}
