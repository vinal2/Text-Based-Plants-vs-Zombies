package model;

public class Zombie {
    private int index;
    private int health;
    private int damage;

    public Zombie(int hp, int dmg, int place){
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
            System.out.println("dead");
            return false;
        }
        return true;
    }

}
