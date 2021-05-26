package Terrarius.Controller.Game;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.buffs.BuffStats;

public class BuffController {
    private final BuffStats buff;
    private final Hero hero;
    private int timePassed;
    private int hpPerSecond;

    public BuffController(BuffStats buff, Hero hero) {
        this.buff = buff;
        this.hero = hero;

        applyInitialBuffs();
        timePassed = 0;
    }

    public boolean updateAndCheckDuration(int timeSinceLastUpdate) {
        timePassed += timeSinceLastUpdate;

        if (timePassed % 1000 == 0)
            hero.setHealth(hero.getStats().getHp() + hpPerSecond);

        if (timePassed >= buff.getDuration() * 1000) {
            removeBuffs();
            return true;
        }
        return false;
    }

    private void applyInitialBuffs() {
        hero.setPower(hero.getStats().getPower() + buff.getPower());
        hero.setRange(hero.getStats().getRange() + buff.getExtraRange());
        hero.setSpeed(hero.getStats().getSpeed() + buff.getSpeedUp());

        if (buff.getDuration() == 0) {
            this.hpPerSecond = 0;
            hero.setHealth(hero.getStats().getHp() + buff.getAmountHP());
        } else {
            this.hpPerSecond = buff.getAmountHP() / buff.getDuration();
            int firstHeal = buff.getAmountHP() % buff.getDuration();
            hero.setHealth(hero.getStats().getHp() + firstHeal + hpPerSecond);
        }
    }

    private void removeBuffs() {
        hero.setPower(hero.getStats().getPower() - buff.getPower());
        hero.setRange(hero.getStats().getRange() - buff.getExtraRange());
        hero.setSpeed(hero.getStats().getSpeed() - buff.getSpeedUp());
    }

    public BuffStats getBuffs() {
        return buff;
    }
}
