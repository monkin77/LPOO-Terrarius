package Viewer.Image;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Model.elements.Hero;
import Model.elements.enemies.Enemy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.Scanner;

public class Image {

    private int height;
    private int width;
    private char[][] aspect;

    public Image(Element element){

        if (Hero.class.equals(element.getClass())) {

            //TODO find a way to not hardcode this
            loadFile("D:\\Faculdade\\2ano\\2semestre\\LPOO\\lpoo-2021-g34\\src\\main\\resources\\Images\\Hero.txt");
        }
        //TODO conditions other elements
    }

    private void loadFile(String path){

        try {
            File imageFile = new File(path);

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


        } catch (FileNotFoundException e) {
            //TODO what to do with th exception
        }
    }

    public void draw(Position position, GUI gui){
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                gui.drawCharacter(position.getX()+j, position.getY()+i, aspect[i][j]);
            }
        }
    }
}
