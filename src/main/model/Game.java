package model;

import java.util.ArrayList;

public class Game {
    private String[] playField;
    private ArrayList<Zombie> zombieList;
    private ArrayList<Plant> plantList;
    private boolean gameState;

    public Game() {
        playField = new String[]{"_", "_", "_", "_", "_", "_", "_", "_", "_", "_"};
        zombieList = new ArrayList<>();
        plantList = new ArrayList<>();
        gameState = true;
        placeZombie(7);
        placeZombie(9);
    }

    // REQUIRES: index >= 0, index < playField.size()
    // MODIFIES: this
    // EFFECTS: places zombie in play field and in zombieList
    public void placeZombie(int index) {
        Zombie zombie = new Zombie(500, 20, index);
        playField[index] = "z";
        zombieList.add(zombie);
    }

    // REQUIRES: index >= 0, index < playField.size()
    // MODIFIES: this
    // EFFECTS: places plant on play field and in plantList
    public void placePlant(int index) {
        Plant plant = new Plant(100, 10, index);
        playField[index] = "p";
        plantList.add(plant);
    }

    // EFFECTS: returns play field array
    public String[] returnPlayField() {
        return playField;
    }

    // EFFECTS: moves the zombies forward, comments in the function explain in further depth
    public void moveZombies() {
        if (detectZombie(closestZombie(), furthestFlower())) {
            int index = 0;
            // then check all subsequent zombies if they can move or not
            for (Zombie z : zombieList) {
                int nextIndex = z.getIndex() - 1;
                // check every zombie's index to see if they are not the closest zombie
                if (z.getIndex() != closestZombie() && !playField[nextIndex].equals("z")) {
                    moveZombie(z, nextIndex, index);
                } // will do nothing if next index is z
                index++;
                // only under the condition that it is not the closest zombie AND
                // the space in front of the zombie is not a zombie does it move
            }
        } else { // if the front most zombie isn't in front of a plant, move all zombies forward
            int index = 0;
            for (Zombie z : zombieList) {
                int nextIndex = z.getIndex() - 1;
                moveZombie(z, nextIndex, index);
                index++;
            }
        }
    }

    // REQUIRES: instantiated zombie, nextIndex = index + 1, nextIndex >= 0
    // MODIFIES: this
    // EFFECTS: copies the zombie at index and sets it at nextIndex, and then removing zombie at index
    public void moveZombie(Zombie z, int nextIndex, int index) {
        playField[nextIndex] = "z";
        playField[z.getIndex()] = "_";
        Zombie cloneZom = new Zombie(z.getHealth(), z.getDamage(), nextIndex);
        zombieList.set(index, cloneZom);
    }

    //EFFECTS: calculates amount of damage done to the first zombie
    public int calculateDamage() {
        int damage = 0;
        for (Plant p : plantList) {
            damage += p.getDamage();
        }
        return damage;
    }

    //EFFECTS: finds the closest zombie by index in zombieList
    public int closestZombie() {
        int smallestIndex = zombieList.get(0).getIndex();
        for (int i = 1; i < zombieList.size(); i++) {
            if (smallestIndex > zombieList.get(i).getIndex()) {
                smallestIndex = zombieList.get(i).getIndex();
            }
        }
        return smallestIndex;
    }

    // REQUIRES: damage > 0
    // MODIFIES: this
    // EFFECTS: damages the zombie at the closest index and if it has hp < 0, it will be removed from the play field
    public void damageZombie(int damage) {
        int index = 0;
        int closestZombie = closestZombie();
        if (zombieList.size() > 0) {
            for (int i = 0; i < zombieList.size(); i++) {
                if (zombieList.get(i).getIndex() == closestZombie) {
                    index = i;
                }
            }
            zombieList.get(index).takeDamage(damage);
            if (zombieList.get(index).isAlive()) {
                zombieList.remove(index);
                playField[closestZombie] = "_";
            }
        }
    }

    //EFFECTS: finds the furthest plant by index
    public int furthestFlower() {
        int largestIndex = 0;
        for (int i = 0; i < plantList.size(); i++) {
            if (largestIndex < plantList.get(i).getIndex()) {
                largestIndex = plantList.get(i).getIndex();
            }
        }
        return largestIndex;
    }

    // REQUIRES: closestZombie, furthestFlower > 0
    // EFFECTS: returns if the closest zombie is in front of the furthest flower
    public boolean detectZombie(int closestZombie, int furthestFlower) {
        if (closestZombie == 1 && furthestFlower == 0 && plantList.size() == 0) {
            return false;
        }
        return (closestZombie == furthestFlower + 1);
    }

    // MODIFIES: this
    // EFFECTS: if a plant's hp drops below 0, remove it from the play field and from the plantList
    public void damagePlant() {
        int plantIndex = 0;
        int zombieIndex = 0;
        if (detectZombie(closestZombie(), furthestFlower())) {
            for (int i = 0; i < plantList.size(); i++) {
                if (plantList.get(i).getIndex() == furthestFlower()) {
                    plantIndex = i;
                }
            }
            for (int i = 0; i < zombieList.size(); i++) {
                if (zombieList.get(i).getIndex() == closestZombie()) {
                    zombieIndex = i;
                }
            }
            if (plantList.get(plantIndex).isAlive(zombieList.get(zombieIndex).getDamage())) {
                playField[plantList.get(plantIndex).getIndex()] = "_";
                plantList.remove(plantIndex);
            }
        }
    }

    // EFFECTS: if the closest zombie is at the beginning of the play field list, the game is over
    public void gameOver() {
        if (closestZombie() == 0) {
            stopGame();
        }
    }

    // EFFECTS: returns game state
    public boolean getGameState() {
        return gameState;
    }

    // MODIFIES: this
    // EFFECTS: stops game
    public void stopGame() {
        gameState = false;
    }

    // EFFECTS: returns zombie list
    public ArrayList<Zombie> getZombieList() {
        return zombieList;
    }

    // EFFECTS: returns plant list
    public ArrayList<Plant> getPlantList() {
        return plantList;
    }

}
