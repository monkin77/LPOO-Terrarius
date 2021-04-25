package Viewer.Image;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Model.elements.enemies.Enemy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Image {

    private int height;
    private int width;
    private char[][] aspect;

    public void load(String fname){

        URL resource = getClass().getClassLoader().getResource(fname);

        try {
            File imageFile = new File(resource.toURI());

            Scanner imageScanner = new Scanner(imageFile);

            height = imageScanner.nextInt();
            width = imageScanner.nextInt();

            aspect = new char[height][width];

            imageScanner.nextLine(); //clears the /n

            for(int i = 0; i < height; i++){

                String data = imageScanner.nextLine();

                for (int j = 0; j < width; j++){
                    int k = data.length();
                    aspect[i][j] = j >= data.length() ? ' ' : data.charAt(j);
                }
            }
        } catch (FileNotFoundException | URISyntaxException e) {
            //TODO what to do with th exception
        }
    }

    public void draw(Element element, GUI gui){
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                gui.drawCharacter(element.getPosition().getX()+j, element.getPosition().getY()+i, aspect[i][j]);
            }
        }
    }
}
