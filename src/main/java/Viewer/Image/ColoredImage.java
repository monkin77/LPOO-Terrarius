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

import static Viewer.ViewerConstants.*;

public class ColoredImage extends StillImage{

    private final Map<Character, String> charColorMap = new HashMap<>();
    private char[][] charColors;
    private final Map<Character, String> bgColorMap = new HashMap<>();
    private char[][] bgColors;

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
            charColors = new char[height][width];
            bgColors = new char[height][width];

            imageScanner.nextLine(); //clears the /n

            for(int i = 0; i < height; i++){

                String data = imageScanner.nextLine();

                for (int j = 0; j < width; j++){
                    aspect[i][j] = j >= data.length() ? ' ' : data.charAt(j);
                }
            }

            int noCharColors = imageScanner.nextInt();

            imageScanner.nextLine();

            for (int i = 0; i< noCharColors; i++){
                Character key;
                key = imageScanner.next().charAt(0);

                String value;
                value = imageScanner.next();

                charColorMap.put(key, value);

                String trash = imageScanner.nextLine();
            }

            for(int i = 0; i < height; i++){

                String data = imageScanner.nextLine();

                for (int j = 0; j < width; j++){
                    charColors[i][j] = data.charAt(j);
                }
            }

            int noBgColors = imageScanner.nextInt();

            imageScanner.nextLine();

            for (int i = 0; i< noBgColors; i++){
                Character key;
                key = imageScanner.next().charAt(0);

                String value;
                value = imageScanner.next();

                bgColorMap.put(key, value);

                String trash = imageScanner.nextLine();
            }

            System.out.println(fname+ "\n");

            for(int i = 0; i < height; i++){

                String data = imageScanner.nextLine();

                for (int j = 0; j < width; j++){
                    bgColors[i][j] = data.charAt(j);
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
                String charColor = DEFAULT_FOREGROUND_COLOR;
                String bgColor = DEFAULT_BACKGROUND_COLOR;

                if(orientation == Element.Orientation.RIGHT){
                    aspect_char = aspect[i][j];
                    bgColor = bgColorMap.get(bgColors[i][j]);
                    charColor = charColorMap.get(charColors[i][j]);
                }
                else{
                    aspect_char = aspect[i][this.dimensions.getWidth() - 1 - j];
                    bgColor = bgColorMap.get(bgColors[i][this.dimensions.getWidth() - 1 - j]);
                    charColor = charColorMap.get(charColors[i][this.dimensions.getWidth() - 1 - j]);
                }

                if (aspect_char == '.') continue;

                gui.drawCharacter(
                        position.getX()+j, position.getY()+i,
                        aspect_char, charColor, bgColor);
            }
        }
    }
}
