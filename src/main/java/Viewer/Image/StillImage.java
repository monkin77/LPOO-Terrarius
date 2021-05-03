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

public class StillImage extends Image{
    protected ImageDimensions dimensions;     // Possibly write about this on report (Primitive Obsession smell fixed)
    protected char[][] aspect;

    public void load(String fname){

        URL resource = getClass().getClassLoader().getResource(fname);

        try {
            File imageFile = new File(resource.toURI());

            Scanner imageScanner = new Scanner(imageFile);

            int height = imageScanner.nextInt();
            int width = imageScanner.nextInt();
            this.dimensions = new ImageDimensions(width, height);

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

    @Override
    public void update() {
        //Not doing anything for now
    }

    @Override
    public void reset() {
        //Not doing anything for now
    }

    public void draw(Position position, GUI gui){
        for(int i = 0; i < this.dimensions.getHeight(); i++){
            for (int j = 0; j < this.dimensions.getWidth(); j++){
                gui.drawCharacter(position.getX()+j, position.getY()+i, aspect[i][j]);
            }
        }
    }

    public ImageDimensions getDimensions() {
        return dimensions;
    }
}
