# My Personal Project

## By Preston San

## User Stories
- As a user, I want to be able to spawn a plant
- As a user, I want to be able to spawn a zombie and have it move
- As a user, I want to be able to hit a zombie with a projectile spawned by a plant
- As a user, I want to be able to kill zombies when they have no health left
- As a user, I want to be able to have plants die when hit enough by zombies
- As a user, I want to be able to create a level of zombies
- As a user, I want to be able to beat said level to win
- I want to be able to load a level of zombies that spawn by a specific round
- I want to be able to save the current level of zombies, as well as all other zombies left in the level
- I want to have the option to not save the level of zombies as I exit the game

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by launching MainGui and pressing the play button
    that loads in the base level, adding 2 zombies into the Game class' playField and displaying it from the GameToGui class
- You can generate the second required event related to adding Xs to a Y by using that same instance as above.
    Input a number from 0-9 (preferably 0) and press the "Place Flower" button right below it to add a flower onto the playField
- You can locate my visual component by launching MainGui. It will launch the main menu with a background image (yes that's minecraft)
- You can save the state of my application by pressing the save button after launching the game instance
    (play/load button in the main menu)
- You can reload the state of my application by pressing the load button in the main menu

# Phase 4: Task 2
- in the case of a win:
- Wed Nov 30 22:33:46 PST 2022
  zombie added at index: 7
  Wed Nov 30 22:33:46 PST 2022
  zombie added at index: 9
  Wed Nov 30 22:33:49 PST 2022
  plant added at index: 0
  Wed Nov 30 22:33:50 PST 2022
  Game won
- in the case of a loss:
- Wed Nov 30 22:34:19 PST 2022
  zombie added at index: 7
  Wed Nov 30 22:34:19 PST 2022
  zombie added at index: 9
  Wed Nov 30 22:34:20 PST 2022
  Game lost
# Phase 4: Task 3
- In the lower levels of the UML diagram, you can observe the GameApp and Game as well as the GameToGui-Game association.
- The classes GameToGui and GameApp handle the game loop for each respective "mode" being console and gui
- I feel like I could've refactored it to have game looping mechanics inside of Game to remove GameApp (for Phase 1)
- However, having it separated into console and GUI versions of handling all the not so game related methods,
- is a benefit for organization.
- Looking at my project, I don't really have regrets on my design of my program, as all the different classes
- have their reason to be separate classes.
- Zombie/Plants on the other hand, could totally be abstract as most of the methods and fields are the same.
- note: sorry that the image is flipped, I'm not sure how my portrait picture got flipped into landscape in intelliJ