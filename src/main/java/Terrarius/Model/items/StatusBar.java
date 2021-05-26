package Terrarius.Model.items;

public class StatusBar {

    private int health;
    private int maxHealth;

    private int xpPoints;

    private int power;

    public StatusBar(int health, int maxHealth, int power){
        this.health = health;
        this.maxHealth = maxHealth;
        this.power = power;
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
}
