package com.lpoo.terrarius.viewer.game;

import com.lpoo.terrarius.gui.GUI;
import com.lpoo.terrarius.model.game.Position;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.model.game.elements.hero.HeroStats;
import com.lpoo.terrarius.model.game.items.buffs.Buff;
import com.lpoo.terrarius.utils.Dimensions;

import java.util.List;

import static com.lpoo.terrarius.utils.GameConstants.STATUS_BAR_BACKGROUND_COLOR;
import static com.lpoo.terrarius.utils.GameConstants.STATUS_BAR_FOREGROUND_COLOR;

public class StatusBarViewer {

    public void draw(Hero hero, Dimensions dimensions, GUI gui){
        Position statusBarPos = new Position(2, dimensions.getHeight() + 1);
        HeroStats heroStats = hero.getStats();
        List<Buff> buffSet = hero.getActiveBuffs();

        for (int i = 0; i < dimensions.getWidth(); i++){
            for (int j = dimensions.getHeight(); j < dimensions.getHeight()+3; j++){
                gui.drawCharacter(i, j, ' ', STATUS_BAR_FOREGROUND_COLOR, STATUS_BAR_BACKGROUND_COLOR);

                if (i == 0 || i == dimensions.getWidth() - 1)
                    gui.drawCharacter(i, j, '|', STATUS_BAR_FOREGROUND_COLOR, STATUS_BAR_BACKGROUND_COLOR);
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
