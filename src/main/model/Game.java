package model;

import java.util.ArrayList;

public class Game {
    public static int ResolutionX = 0;
    public static int ResolutionY = 0;
    private String[] playField;
    private ArrayList<Zombie> zombieList;
    private ArrayList<Plant> plantList;
    private String field;
    public boolean gameState;

    public Game(){
        playField = new String[]{"_", "_", "_", "_", "_", "_", "_", "_", "_", "_"};
        zombieList = new ArrayList<>();
        plantList = new ArrayList<>();
        gameState = true;
        placeZombie(7);
        placeZombie(9);
    }

    public String[] placeZombie(int index) {
        Zombie zombie = new Zombie(500, 10, index);
        playField[index] = "z";
        zombieList.add(zombie);
        return playField;
    }

    public String[] placePlant(int index) {
        Plant plant = new Plant(100, 10, index);
        playField[index] = "p";
        plantList.add(plant);
        return playField;
    }

    public String[] returnPlayField(){
        return playField;
    }

    public String[] moveZombie(){ //pass in array of locations of zombies on the field
        for (int i = 0; i < zombieList.size(); i++){
            if (i > 0 | !detectZombie(closestZombie(), furthestFlower())){
                int newLocation = zombieList.get(i).getIndex() - 1;
                playField[newLocation] = "z";
                playField[zombieList.get(i).getIndex()] = "_";
                Zombie clonedZombie = new Zombie(zombieList.get(i).getHealth(), zombieList.get(i).getDamage(), newLocation);
                zombieList.set(i, clonedZombie);
                if (i > 0 & playField[newLocation].equals("z")) {

                }
            }
        }
        return playField;
    }

    public int calculateDamage(){ // calculates amount of damage done to the first zombie
        int damage = 0;
        for (int i = 0; i < plantList.size(); i++) {
           damage += plantList.get(i).getDamage();
        }
        return damage;
    }

    public int closestZombie(){
        int smallestIndex = 0;
        for (int i = 0; i < zombieList.size(); i++) {
            if (i == 0){
                smallestIndex = zombieList.get(i).getIndex();
            } else if (smallestIndex > zombieList.get(i).getIndex()){
                smallestIndex = zombieList.get(i).getIndex();
            }

        }
        return smallestIndex;
    }
    public String[] damageZombie(int damage){
        int index = 0;
        int closestZombie = closestZombie();
        if (zombieList.size() > 0) {
            for (int i = 0; i < zombieList.size(); i++) {
                if (zombieList.get(i).getIndex() == closestZombie) {
                    index = i;
                }
            }
            if(!zombieList.get(index).isAlive(damage)){
                zombieList.remove(index);
                playField[closestZombie] ="_";
                // System.out.println("Zombie in index " + (closestZombie + 1) + " has died from " + damage + " damage.");
            }
        }
        return playField;
    }

    public int furthestFlower(){
        int largestIndex = 0;
        for (int i = 0; i < plantList.size(); i++) {
            if (i == 0){
                largestIndex = plantList.get(i).getIndex();
            } else if (largestIndex < plantList.get(i).getIndex()){
                largestIndex = plantList.get(i).getIndex();
            }
        }
        return largestIndex;
    }

    public boolean detectZombie(int closestZombie, int furthestFlower) {
        if (closestZombie == furthestFlower + 1) {
            return true;
        }
        return false;
    }

    public String[] damagePlant(){
        int plantIndex = 0;
        int zombieIndex = 0;
        if (detectZombie(closestZombie(), furthestFlower())) {
            for (int i = 0; i < plantList.size(); i++) {
                if (plantList.get(i).getIndex() == furthestFlower()) {
                    plantIndex = i;
                }
            }
            for (int i = 0; i < zombieList.size(); i++){
                if (zombieList.get(i).getIndex() == closestZombie()){
                    zombieIndex = i;
                }
            }
            if (!plantList.get(plantIndex).isAlive(zombieList.get(zombieIndex).getDamage())){
                System.out.println("plant dead");
                playField[plantList.get(plantIndex).getIndex()] = "_";
                plantList.remove(plantIndex);
            }
        }
        return playField;
    }

    public boolean gameOver() {
        if (closestZombie() == 0) {
            gameState = false;
        }
        return gameState;
    }

}
