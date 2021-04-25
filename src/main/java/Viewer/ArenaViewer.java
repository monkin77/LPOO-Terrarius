package Viewer;

import GUI.GUI;
import Model.Position;
import Model.elements.Element;
import Model.elements.Hero;
import Model.elements.enemies.Enemy;
import Viewer.Image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaViewer {

    private Map<Class, ElementViewer> viewerCache;

    private final GUI gui;

    public ArenaViewer(GUI gui){
        this.gui = gui;
    }

    public void draw(){ //Arena argument


    }

}
