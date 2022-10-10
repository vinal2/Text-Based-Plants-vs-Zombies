package model;

public class Plant {
    public static double Attack_Speed = 0.5;
    public int coordinateX;
    public int coordinateY;
    private int health;
    private int damage;
    private int index;

    public Plant(int hp, int dmg, int place) {
        health = hp;
        damage = dmg;
        index = place;
    }

    public int getHealth(){
        return health;
    }

    public int getDamage(){
        return damage;
    }

    public int getIndex(){
        return index;
    }

    public boolean isAlive(int damage) {
        health -= damage;
        if (health <= 0) {
            return false;
        }
        return true;
    }

}
