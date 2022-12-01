package model;

import org.json.JSONObject;

// this class is for plants, which are static in the play field and do damage over time to zombies. Placed by the player
public class Plant {
    private int health;
    private final int damage;
    private final int index;

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

    // EFFECTS: turns plant into json object
    public JSONObject toJson() {
        JSONObject plant = new JSONObject();
        plant.put("health", health);
        plant.put("damage", damage);
        plant.put("index", index);
        return plant;
    }

}
