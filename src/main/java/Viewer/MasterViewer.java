package Viewer;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Model.elements.Hero;
import Viewer.Image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterViewer {

    private final Map<Class, Image> imageCache = new HashMap<>(); //TODO kind of a code smell

    private final List<Element> tempElemList = new ArrayList<>();

    private final GUI gui;

    public MasterViewer(GUI gui){

        this.gui = gui;

        tempElemList.add(new Hero(new Position(10, 10)));
        tempElemList.add(new Hero(new Position(20, 15)));

        for (int i = 0; i < tempElemList.size(); i++){
            if(!imageCache.containsKey(tempElemList.get(i).getClass())){

                Image image = new Image(tempElemList.get(i));

                imageCache.put(tempElemList.get(i).getClass(), image);
            }
        }

    }

    public void draw(){ //TODO This is supposed to have something like Arena has argument, for now I'll be using a simple element list

        for (int i = 0; i < tempElemList.size(); i++){

            Image image = imageCache.get(tempElemList.get(i).getClass());

            image.draw(tempElemList.get(i).getPosition(), gui);

        }

    }

}
