package Terrarius.Model.elements.status;

import Terrarius.Model.items.StatusBar;

public class SteadyStatusEffect extends StatusEffect{

    private Boolean applied;
    private int initalValue;

    public SteadyStatusEffect(int power, EffectType effectType, int duration) {
        super(power, effectType, duration);
        this.applied = false;
    }

    @Override
    public void apply(StatusBar statusBar) {
        if (duration <= 0){
            switch (super.effectType){
                case POWER:
                    statusBar.setPower(initalValue);
                    break;
                case HEALTH:
                    statusBar.setHealth(initalValue);
                    break;
            }
        }
        else if(!applied){
            switch (super.effectType){
                case POWER:
                    statusBar.modifyPower(super.power);
                    break;
                case HEALTH:
                    statusBar.modifyHealth(super.power);
                    break;
            }
            applied = true;
        }
        duration--;
    }
}
