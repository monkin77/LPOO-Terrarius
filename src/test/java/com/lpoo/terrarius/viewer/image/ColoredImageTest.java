package com.lpoo.terrarius.viewer.image;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ColoredImageTest {
    private ColoredImage coloredImage;
    private GUI gui;

    @BeforeEach
    public void setup() {
        coloredImage = new ColoredImage();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void load() throws FileNotFoundException, URISyntaxException {
        Scanner imageScanner = Mockito.mock(Scanner.class);
        Mockito.when(imageScanner.nextInt()).thenReturn(100);

        coloredImage = Mockito.spy(coloredImage);
        Mockito.doReturn(imageScanner).when(coloredImage).getScannerFromFile(Mockito.anyString());
        Mockito.doNothing().when(coloredImage).loadAspect(imageScanner, 100, 100);
        Mockito.doNothing().when(coloredImage).loadCharColors(imageScanner, 100, 100);
        Mockito.doNothing().when(coloredImage).loadBackgroundColors(imageScanner, 100, 100);

        coloredImage.load("Test");
        Mockito.verify(coloredImage, Mockito.times(1)).getScannerFromFile("Test");
        Mockito.verify(coloredImage, Mockito.times(1)).loadAspect(imageScanner, 100, 100);
        Mockito.verify(coloredImage, Mockito.times(1)).loadCharColors(imageScanner, 100, 100);
        Mockito.verify(coloredImage, Mockito.times(1)).loadBackgroundColors(imageScanner, 100, 100);
    }

    @Test
    public void loadNoFile() {
        Assertions.assertThrows(NullPointerException.class, () -> coloredImage.load("Test.txt"));
    }

    @Test
    public void loadCharColors() {
        Scanner imageScanner = Mockito.mock(Scanner.class);
        Mockito.when(imageScanner.nextInt()).thenReturn(2);
        Mockito.when(imageScanner.nextLine()).thenReturn("abc");
        Mockito.when(imageScanner.next()).thenReturn("a");

        coloredImage.loadCharColors(imageScanner, 3, 3);

        Mockito.verify(imageScanner, Mockito.times(1)).nextInt();
        Mockito.verify(imageScanner, Mockito.times(6)).nextLine();
        Mockito.verify(imageScanner, Mockito.times(4)).next();
    }

    @Test
    public void loadBackgroundColors() {
        Scanner imageScanner = Mockito.mock(Scanner.class);
        Mockito.when(imageScanner.nextInt()).thenReturn(2);
        Mockito.when(imageScanner.nextLine()).thenReturn("abc");
        Mockito.when(imageScanner.next()).thenReturn("a");

        coloredImage.loadCharColors(imageScanner, 3, 3);

        Mockito.verify(imageScanner, Mockito.times(1)).nextInt();
        Mockito.verify(imageScanner, Mockito.times(6)).nextLine();
        Mockito.verify(imageScanner, Mockito.times(4)).next();
    }

    @Test
    public void draw() throws FileNotFoundException, URISyntaxException {
        coloredImage.load("Images/Blocks/WoodBlock.txt");
        coloredImage.draw(new Position(10, 10), Element.Orientation.RIGHT, gui);

        Mockito.verify(gui, Mockito.times(16)).
                drawCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar(),
                                Mockito.anyString(), Mockito.anyString());

        coloredImage.draw(new Position(10, 10), Element.Orientation.LEFT, gui);

        Mockito.verify(gui, Mockito.times(32)).
                drawCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar(),
                                Mockito.anyString(), Mockito.anyString());
    }
}
