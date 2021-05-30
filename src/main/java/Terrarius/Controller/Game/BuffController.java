package Terrarius.Controller.Game;

import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.items.buffs.Buff;

public class BuffController {

    private static final int TIME_PER_BUFF_EFFECT = 1000;

    private final Buff buff;
    private final Hero hero;
    private int timePassed;
    private int hpPerSecond;


    public BuffController(Buff buff, Hero hero) {
        this.buff = buff;
        this.hero = hero;

        applyInitialBuffs();
        timePassed = 0;
    }

    public boolean updateAndCheckDuration(int timeSinceLastUpdate) {
        timePassed += timeSinceLastUpdate;

        if (timePassed >= TIME_PER_BUFF_EFFECT) {
            hero.setHealth(hero.getStats().getHp() + hpPerSecond);
            buff.getStats().decreaseDuration();
            timePassed = 0;
        }

        if (buff.getStats().getDuration() <= 0) {
            removeBuffs();
            return true;
        }
        return false;
    }

    private void applyInitialBuffs() {
        hero.setPower(hero.getStats().getPower() + buff.getStats().getPower());
        hero.setRange(hero.getStats().getRange() + buff.getStats().getExtraRange());
        hero.setSpeed(hero.getStats().getSpeed() + buff.getStats().getSpeedUp());

        if (buff.getStats().getDuration() == 0) {
            this.hpPerSecond = 0;
            hero.setHealth(hero.getStats().getHp() + buff.getStats().getAmountHP());
        } else {
            this.hpPerSecond = buff.getStats().getAmountHP() / buff.getStats().getDuration();
            int firstHeal = buff.getStats().getAmountHP() % buff.getStats().getDuration();
            hero.setHealth(hero.getStats().getHp() + firstHeal + hpPerSecond);
        }
    }

    private void removeBuffs() {
        hero.setPower(hero.getStats().getPower() - buff.getStats().getPower());
        hero.setRange(hero.getStats().getRange() - buff.getStats().getExtraRange());
        hero.setSpeed(hero.getStats().getSpeed() - buff.getStats().getSpeedUp());
        hero.removeBuff(buff);
    }
}
