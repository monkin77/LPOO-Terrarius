package Terrarius.Model.elements.status;

public class Strength extends SteadyStatusEffect{
    public Strength(int power,  int duration) {
        super("Strength", EffectType.POWER, power, duration);
    }
}
