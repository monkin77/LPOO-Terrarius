package Terrarius.Model.elements.status;

import Terrarius.Model.items.StatusBar;

public abstract class StatusEffect {
    public enum EffectType {HEALTH, MAX_HEALTH, POWER};

    protected String name;
    protected EffectType effectType;
    protected int power;
    protected int duration;

    public StatusEffect(String name, EffectType effectType, int power,  int duration){
        this.name = name;
        this.duration = duration;
        this.effectType = effectType;
        this.power = power;
    }

    public abstract void apply(StatusBar statusBar);

    public int getDuration(){
        return duration;
    }
}
