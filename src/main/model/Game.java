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
                if (z.getIndex() != closestZombie() && playField[nextIndex] != "z") {
                    moveZombie(z, nextIndex, index);
                } // will do nothing if next index is z
                index++;
                // only under the condition that it is not the closest zombie AND the space in front of the zombie is not a zombie does it move
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


    public int calculateDamage() { // calculates amount of damage done to the first zombie
        int damage = 0;
        for (int i = 0; i < plantList.size(); i++) {
            damage += plantList.get(i).getDamage();
        }
        return damage;
    }

    public int closestZombie() {
        int smallestIndex = 0;
        for (int i = 0; i < zombieList.size(); i++) {
            if (i == 0) {
                smallestIndex = zombieList.get(i).getIndex();
            } else if (smallestIndex > zombieList.get(i).getIndex()) {
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

    public int furthestFlower() {
        int largestIndex = 0;
        for (int i = 0; i < plantList.size(); i++) {
            if (i == 0) {
                largestIndex = plantList.get(i).getIndex();
            } else if (largestIndex < plantList.get(i).getIndex()) {
                largestIndex = plantList.get(i).getIndex();
            }
        }
        return largestIndex;
    }

    public boolean detectZombie(int closestZombie, int furthestFlower) {
        return (closestZombie == furthestFlower + 1);
    }

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
                System.out.println("plant dead");
                playField[plantList.get(plantIndex).getIndex()] = "_";
                plantList.remove(plantIndex);
            }
        }
    }

    public void gameOver() {
        if (closestZombie() == 0) {
            gameState = false;
        }
    }

    public boolean getGameState() {
        return gameState;
    }

    public void stopGame() {
        gameState = false;
    }

}
