package model;

import org.json.JSONObject;

// this class is for zombies, which move every round and eat the plants placed by the player, if they get to the left
// side of the play field, you lose
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

    // REQUIRES: damage > 0
    // MODIFIES: this
    // EFFECTS: returns if the zombie is dead or alive
    public void takeDamage(int damage) {
        health -= damage;
    }

    // EFFECTS: turns the zombie into a json object
    public JSONObject toJson() {
        JSONObject zombie = new JSONObject();
        zombie.put("health", health);
        zombie.put("damage", damage);
        zombie.put("index", index);
        return zombie;
    }
}
