package Terrarius.Model.items;

import Terrarius.Model.Level;
import Terrarius.Model.elements.status.StatusEffect;

import java.util.ArrayList;
import java.util.List;

public class StatusBar {

    private int health;
    private int maxHealth;

    private Level level;

    private int power;

    private final List<StatusEffect> statusEffects = new ArrayList<>();

    public StatusBar(int health, int maxHealth, int power, Level level){
        this.health = health;
        this.maxHealth = maxHealth;
        this.power = power;
        this.level = level;
    }

    public List<StatusEffect> getStatusEffects(){
        return statusEffects;
    }

    public void addStatusEffect(StatusEffect statusEffect){
        statusEffects.add(statusEffect);
    }

    public void applyStatusEffects(){ //TODO should this be done in a statusController or smt like that

        List<StatusEffect> removeStatusEffects = new ArrayList<>();

        for (StatusEffect statusEffect : statusEffects){
            statusEffect.apply(this);
            if (statusEffect.getDuration() <= 0) removeStatusEffects.add(statusEffect);
        }

        for (StatusEffect statusEffect : removeStatusEffects){
            statusEffects.remove(statusEffect);
        }
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth(){
        return this.health;
    }

    public int modifyHealth(int increment){
        this.health += increment;
        if (this.health <0 ) this.health = 0;
        if (this.health > this.maxHealth) this.health = this.maxHealth;
        return this.health;
    }

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }

    public int modifyMaxHealth(int increment){
        this.maxHealth += increment;
        if (this.maxHealth < 0 ) this.maxHealth = 0;
        return this.maxHealth;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int modifyPower(int increment){
        this.power += increment;
        if (this.power < 0 ) this.power= 0;
        return this.power;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
