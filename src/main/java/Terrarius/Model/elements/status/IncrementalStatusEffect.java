package Terrarius.Model.elements.status;

import Terrarius.Model.items.StatusBar;

public class IncrementalStatusEffect extends StatusEffect{

    public IncrementalStatusEffect(String name, EffectType effectType, int power,  int duration) {
        super(name, effectType, power, duration);
    }

    @Override
    public void apply(StatusBar statusBar) {

        if (duration <= 0) return;
        duration--;

        switch (super.effectType){
            case POWER:
                statusBar.modifyPower(super.power);
                break;
            case HEALTH:
                statusBar.modifyHealth(super.power);
                break;
        }
    }
}
