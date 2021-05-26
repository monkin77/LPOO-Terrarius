package Terrarius.Viewer.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Dimensions;
import Terrarius.Model.Position;
import Terrarius.Model.elements.Element;
import Terrarius.Model.elements.status.StatusEffect;
import Terrarius.Model.items.StatusBar;
import Terrarius.Model.items.Toolbar;

import static Terrarius.Viewer.Game.GameViewerConstants.*;

public class StatusbarViewer {

    private static final String STATUS_BAR_BACKGROUND_COLOR = "#AA5518";
    private static final String STATUS_BAR_FOREGROUND_COLOR = "#FFFFFF";

    //TODO maybe add images l8r

    public void draw(StatusBar statusBar, Dimensions dimensions, GUI gui){
        Position statusBarPos = new Position(2, dimensions.getHeight() + 1);

        for (int i = 0; i < dimensions.getWidth(); i++){
            for (int j = dimensions.getHeight(); j < dimensions.getHeight()+3; j++){
                gui.drawCharacter(i, j, ' ', STATUS_BAR_FOREGROUND_COLOR, STATUS_BAR_BACKGROUND_COLOR);
            }
        }

        String statsString = "hp: " + statusBar.getHealth() + "/" + statusBar.getMaxHealth() +
                " lvl: " + statusBar.getLevel().getNumLevel() + " xp: " + statusBar.getLevel().getCurrentXP() +
                " PWR: " + statusBar.getPower();

        for (StatusEffect statusEffect : statusBar.getStatusEffects()){

            if (statsString.length() > dimensions.getWidth() - 4){
                statsString = statsString.substring(0, dimensions.getWidth() - 4);
                break;
            }
            else if(statsString.length() == dimensions.getWidth() - 4){
                break;
            }

            statsString += " " + statusEffect.getName() + ": " + statusEffect.getDuration();
        }


        gui.drawString(statusBarPos.getX(), statusBarPos.getY(), statsString, STATUS_BAR_FOREGROUND_COLOR, STATUS_BAR_BACKGROUND_COLOR);
    }

}
