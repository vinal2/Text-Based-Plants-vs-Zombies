package model;

public class Zombie {
    private int index;
    private int health;
    private int damage;

    public Zombie(int hp, int dmg, int place) {
        health = hp;
        damage = dmg;
        index = place;
    }

    // EFFECTS: returns health
    public int getHealth() {
        return health;
    }

    // EFFECTS: returns damage
    public int getDamage() {
        return damage;
    }

    // EFFECTS: returns index
    public int getIndex() {
        return index;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: returns if the zombie is dead or alive
    public boolean isAlive() {
        return (health <= 0);
    }

    public void takeDamage(int damage) {
        health -= damage;
    }
}
