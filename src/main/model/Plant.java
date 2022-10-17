package model;

public class Plant {
    private int health;
    private int damage;
    private int index;

    public Plant(int hp, int dmg, int place) {
        health = hp;
        damage = dmg;
        index = place;
    }

    // EFFECTS: returns health
    public int getHealth() {
        return health;
    }

    // EFFECTS: returns
    public int getDamage() {
        return damage;
    }

    // EFFECTS: returns index of plant
    public int getIndex() {
        return index;
    }

    // REQUIRES: 0 < int damage
    // MODIFIES: this
    // EFFECTS: returns if the plant is dead or alive after damaging it
    public boolean isAlive(int damage) {
        health -= damage;
        return (health <= 0);
    }

}
