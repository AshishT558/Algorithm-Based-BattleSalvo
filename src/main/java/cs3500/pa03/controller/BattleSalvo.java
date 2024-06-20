package cs3500.pa03.controller;

import cs3500.pa03.model.AIPlayer;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.HumanPlayer;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.view.TerminalBoard;
import cs3500.pa03.view.TerminalMenu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * controller class that runs while game is played
 */
public class BattleSalvo {
  private static Scanner readInput;
  private HumanPlayer user;
  private AIPlayer ai;
  public BattleSalvo(Scanner s) {
    readInput = s;
    user = new HumanPlayer();
    ai = new AIPlayer();

  }

  /**
   * starting the battlesalvo game by initalizing fields and players
   * @param tester whether its being used to test players
   */
  public void startGame(boolean tester) {
    //display the welcome message and board size question
    TerminalMenu.displayMenu(1, 0);
    boolean setupInProg = true;
    while(setupInProg) {
      //if user has an answer
      if(readInput.hasNextLine()) {
        int height = readInput.nextInt();
        int width = readInput.nextInt();
        //checking [6,15]
        if((height >= 6 && height <= 15)
            && (width >= 6 && width <= 15)) {
          //which is smaller: width or height?
          int minOfDims = Math.min(height, width);
          //display the fleet size question
          TerminalMenu.displayMenu(3, minOfDims);
          boolean gettingSizes = true;
          while(gettingSizes) {
            //if user has an answer to fleet size
            if(readInput.hasNextLine()) {
              int carrierCount = readInput.nextInt();
              int battleshipCount = readInput.nextInt();
              int destroyerCount = readInput.nextInt();
              int submarineCount = readInput.nextInt();
              int totalFleet = carrierCount +  battleshipCount + destroyerCount + submarineCount;
              //is fleet size positive and less than maximum board?
              if((carrierCount > 0) &&
                  (battleshipCount > 0) &&
                  (destroyerCount > 0) &&
                  (submarineCount > 0) &&
                  (totalFleet <= minOfDims)){
                //adding the number of ships to a map of ships:occurrences
                HashMap<ShipType, Integer> specifications = new HashMap<>();
                specifications.put(ShipType.CARRIER, carrierCount);
                specifications.put(ShipType.BATTLESHIP, battleshipCount);
                specifications.put(ShipType.DESTROYER, destroyerCount);
                specifications.put(ShipType.SUBMARINE, submarineCount);
                //setup the user(human player) with the given information
                user.setup(height, width, specifications);
                TerminalMenu.displayCustom("Your Board:");
                if(tester) {
                  TerminalBoard.displayBoard(1, user.opponentBoard);
                }
                else {
                  TerminalBoard.displayBoard(1, user.board);
                }
                TerminalMenu.displayCustom("\nOpponent Board:");
                TerminalBoard.displayBoard(1, user.opponentBoard);
                //setup the ai with the given information
                ai.setup(height, width, specifications);
                gettingSizes = false;
              }
              //invalid fleet size
              else {
                TerminalMenu.displayMenu(4, minOfDims);
              }
            }
          } setupInProg = false;
        }
        //invalid board dimensions
        else {
          TerminalMenu.displayMenu(2, 0);
        }
      }
    }
    // go to taking shots stage
    if(tester) {
      System.out.println("end");
    }
    else {
      salvoStage(false);
    }
  }

  /**
   * the salvo stage where shots are concurrently taken
   * @param tester whether the salvostage is being used for testing
   */
  public void salvoStage(boolean tester) {
    while(user.numShips() > 0 && ai.numShips() > 0) {
      //list of all shots taken by users
      ArrayList<Coord> listAllShotsUser1 = user.takeShots();
      ArrayList<Coord> listAllShotsUser2 = ai.takeShots();
      TerminalMenu.displayCustom("\nYour Shots:");
      for(Coord c : listAllShotsUser1) {
        System.out.println(c.x + " " + c.y);
      }
      TerminalMenu.displayCustom("\nOpponent Shots:");
      for(Coord c : listAllShotsUser2) {
        System.out.println(c.x + " " + c.y);
      }
      //check which shots hit opponent ships
      ArrayList<Coord> user2Hit1 = user.reportDamage(listAllShotsUser2);
      ArrayList<Coord> user1Hit2 = ai.reportDamage(listAllShotsUser1);
      //show hit shots on opponent's board
      user.successfulHits(user1Hit2);
      ai.successfulHits(user2Hit1);
      //show self boards with updated opponent shots
      TerminalMenu.displayCustom("Your Board:");
      TerminalBoard.displayBoard(1, user.board);
      TerminalMenu.displayCustom("\nOpponent Board:");
      TerminalBoard.displayBoard(1, user.opponentBoard);
      if(tester) {
        break;
      }
    }
    //check after all salvos who still has ships left, call end of game method
    if(user.numShips() > 0 && !tester) {
      postGame("user");
    }
    else if(ai.numShips() > 0 && !tester) {
      postGame("ai");
    }
    else if(!tester){
      postGame("draw");
    }
    else if(tester) {
      System.out.println("end");
    }
  }

  /**
   * the end of battlesalvo, where the end result is determined
   * @param end who won/ the result of the game
   */
  public void postGame(String end) {
    //user wins
    if(end.equals("user")) {
      TerminalMenu.displayCustom("YOU WIN!");
    }
    //ai wins
    else if(end.equals("ai")) {
      TerminalMenu.displayCustom("YOU LOSE");
    }
    //drawed
    else {
      TerminalMenu.displayCustom("DRAW");
    }
  }
}
