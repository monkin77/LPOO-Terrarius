package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Viewer.Image.AnimatedImage;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class HeroViewer implements ElementViewer<Hero> {
    AnimatedImage image;

    public HeroViewer(){
        image = new AnimatedImage();
        try {
            getImage().load("Images/Hero/Hero.txt");
        } catch (FileNotFoundException | URISyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        image.update();
    }

    public AnimatedImage getImage() {
        return image;
    }

    @Override
    public void draw(Hero hero, GUI gui){
        getImage().draw(hero.getPosition(), hero.getOrientation(), gui);
    }
}
