package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Viewer.Image.AnimatedImage;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class HeroViewer extends ElementViewer<Hero, AnimatedImage> {
    public void update() {
        image.update();
    }

    @Override
    public void draw(Hero hero, GUI gui){
        image.draw(hero.getPosition(), hero.getOrientation(), gui);
    }

    @Override
    public AnimatedImage createImage() {
        AnimatedImage image = new AnimatedImage();
        try {
            image.load("Images/Hero/Hero.txt");
        } catch (FileNotFoundException | URISyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        return image;
    }
}
