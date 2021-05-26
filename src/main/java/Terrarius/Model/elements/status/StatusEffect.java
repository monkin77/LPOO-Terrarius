package Terrarius.Model.elements.status;

import Terrarius.Model.items.StatusBar;

public abstract class StatusEffect {
    public enum EffectType {HEALTH, MAX_HEALTH, POWER};

    protected String name;
    protected int power;
    protected EffectType effectType;
    protected int duration;

    public StatusEffect(int power, EffectType effectType, int duration){
        this.duration = duration;
        this.effectType = effectType;
        this.power = power;
    }

    public abstract void apply(StatusBar statusBar);
}
