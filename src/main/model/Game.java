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

    public void placeZombie(int index) {
        Zombie zombie = new Zombie(500, 20, index);
        playField[index] = "z";
        zombieList.add(zombie);
    }

    public void placePlant(int index) {
        Plant plant = new Plant(100, 10, index);
        playField[index] = "p";
        plantList.add(plant);
    }

    public String[] returnPlayField() {
        return playField;
    }

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

    public void moveZombie(Zombie z, int nextIndex, int index) {
        playField[nextIndex] = "z";
        playField[z.getIndex()] = "_";
        Zombie cloneZom = new Zombie(z.getHealth(), z.getDamage(), nextIndex);
        zombieList.set(index, cloneZom);
    }

    //EFFECTS:
    public int calculateDamage() { // calculates amount of damage done to the first zombie
        int damage = 0;
        for (Plant p : plantList) {
            damage += p.getDamage();
        }
        return damage;
    }

    //EFFECTS:
    public int closestZombie() {
        int smallestIndex = zombieList.get(0).getIndex();
        for (int i = 1; i < zombieList.size(); i++) {
            if (smallestIndex > zombieList.get(i).getIndex()) {
                smallestIndex = zombieList.get(i).getIndex();
            }
        }
        return smallestIndex;
    }

    public void damageZombie(int damage) {
        int index = 0;
        int closestZombie = closestZombie();
        if (zombieList.size() > 0) {
            for (int i = 0; i < zombieList.size(); i++) {
                if (zombieList.get(i).getIndex() == closestZombie) {
                    index = i;
                }
            }
            if (!zombieList.get(index).isAlive(damage)) {
                zombieList.remove(index);
                playField[closestZombie] = "_";
                //System.out.println("Zombie in index " + (closestZombie + 1) + " died from " + damage + " damage.");
            }
        }
    }

    //EFFECTS:
    public int furthestFlower() {
        int largestIndex = 0;
        for (int i = 0; i < plantList.size(); i++) {
            if (largestIndex < plantList.get(i).getIndex()) {
                largestIndex = plantList.get(i).getIndex();
            }
        }
        return largestIndex;
    }


    public boolean detectZombie(int closestZombie, int furthestFlower) {
        if (closestZombie == 1 && furthestFlower == 0 && plantList.size() == 0) {
            return false;
        }
        return (closestZombie == furthestFlower + 1);
    }

    //MODIFIES: this
    //EFFECTS: if a plant's hp drops below 0, remove it from the play field and from the plantList
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
            if (!plantList.get(plantIndex).isAlive(zombieList.get(zombieIndex).getDamage())) {
                playField[plantList.get(plantIndex).getIndex()] = "_";
                plantList.remove(plantIndex);
            }
        }
    }

    //EFFECTS:
    public void gameOver() {
        if (closestZombie() == 0) {
            gameState = false;
        }
    }

    public boolean getGameState() {
        return gameState;
    }

    //EFFECTS:
    public void stopGame() {
        gameState = false;
    }

}
