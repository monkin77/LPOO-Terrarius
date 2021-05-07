package Viewer.Image;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ColoredImage extends StillImage{

    private int no_colors;
    private Map<Character, String> colorMap = new HashMap<>();
    private char[][] colors;

    @Override
    public void load(String fname) {

        URL resource = getClass().getClassLoader().getResource(fname);

        try {
            File imageFile = new File(resource.toURI());

            Scanner imageScanner = new Scanner(imageFile);

            int height = imageScanner.nextInt();
            int width = imageScanner.nextInt();
            this.dimensions = new ImageDimensions(width, height);

            aspect = new char[height][width];
            colors = new char[height][width];

            imageScanner.nextLine(); //clears the /n

            for(int i = 0; i < height; i++){

                String data = imageScanner.nextLine();

                for (int j = 0; j < width; j++){
                    int k = data.length();
                    aspect[i][j] = j >= data.length() ? ' ' : data.charAt(j);
                }
            }

            no_colors = imageScanner.nextInt();

            imageScanner.nextLine();

            for (int i = 0; i<no_colors; i++){
                Character key;
                key = imageScanner.next().charAt(0);

                String value;
                value = imageScanner.next();

                colorMap.put(key, value);

                String trash = imageScanner.nextLine();
            }

            for(int i = 0; i < height; i++){

                String data = imageScanner.nextLine();

                for (int j = 0; j < width; j++){
                    int k = data.length();
                    colors[i][j] = data.charAt(j);
                }
            }


        } catch (FileNotFoundException | URISyntaxException e) {
            //TODO what to do with th exception
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void draw(Position position, Element.Orientation orientation, GUI gui) {
        for(int i = 0; i < this.dimensions.getHeight(); i++){
            for (int j = 0; j < this.dimensions.getWidth(); j++){

                char aspect_char = ' ';
                String color = "#DDDDDD";

                if(orientation == Element.Orientation.RIGHT){
                    aspect_char = aspect[i][j];
                    color = colorMap.get(colors[i][j]);
                }
                else{
                    aspect_char = aspect[i][this.dimensions.getWidth() - 1 - j];
                    color = colorMap.get(colors[i][this.dimensions.getWidth() - 1 - j]);
                }

                gui.drawCharacter(
                        position.getX()+j, position.getY()+i,
                        aspect_char, color);
            }
        }
    }
}
