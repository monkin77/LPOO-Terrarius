package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Model.Game.items.buffs.Buff;
import Terrarius.Utils.Dimensions;

import java.util.List;

public class StatusBarViewer {

    private static final String STATUS_BAR_BACKGROUND_COLOR = "#AA5518";
    private static final String STATUS_BAR_FOREGROUND_COLOR = "#FFFFFF";

    public void draw(Hero hero, Dimensions dimensions, GUI gui){
        Position statusBarPos = new Position(2, dimensions.getHeight() + 1);
        HeroStats heroStats = hero.getStats();
        List<Buff> buffSet = hero.getActiveBuffs();

        for (int i = 0; i < dimensions.getWidth(); i++){
            for (int j = dimensions.getHeight(); j < dimensions.getHeight()+3; j++){
                gui.drawCharacter(i, j, ' ', STATUS_BAR_FOREGROUND_COLOR, STATUS_BAR_BACKGROUND_COLOR);
            }
        }

        String statsString = "hp: " + heroStats.getHp() + "/" + heroStats.getMaxHP() +
                " lvl: " + heroStats.getCurrentLevel() + " xp: " + heroStats.getLevel().getCurrentXP() +
                "/" + heroStats.getLevel().getMaxXP() + " PWR: " + heroStats.getPower();

        for (Buff buff : buffSet) {

            if (statsString.length() > dimensions.getWidth() - 4) {
                statsString = statsString.substring(0, dimensions.getWidth() - 7) + "...";
                break;
            } else if(statsString.length() == dimensions.getWidth() - 4) {
                break;
            }

            statsString += " " + buff.getComponentName() + ": " + buff.getStats().getDuration();
        }

        gui.drawString(statusBarPos.getX(), statusBarPos.getY(), statsString, STATUS_BAR_FOREGROUND_COLOR, STATUS_BAR_BACKGROUND_COLOR);
    }
}