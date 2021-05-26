package Terrarius.Model.elements.status;

public class Regeneration extends IncrementalStatusEffect{
    public Regeneration(int power,  int duration) {
        super("Regeneration", EffectType.HEALTH, power, duration);
    }
}
