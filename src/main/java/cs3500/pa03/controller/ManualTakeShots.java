package cs3500.pa03.controller;

import cs3500.pa03.model.Coord;
import cs3500.pa03.view.TerminalMenu;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class to read player's shots and compile them
 */
public class ManualTakeShots {
  private static Scanner readInput = new Scanner(System.in);
  public ManualTakeShots(Scanner s) {
    readInput = s;
  }

  /**
   * method that takes in parameters for shotsvia command line and returns them in a list
   * @param numShots number of shots that can be entered
   * @param height max height of grid
   * @param width max width of grid
   * @return the list of shots the player has selected
   */
  public static ArrayList<Coord> readShots(int numShots, int height, int width) {
    ArrayList<Coord> takenShots = new ArrayList<>();
    int remainingShots = numShots;
    TerminalMenu.displayMenu(5, remainingShots);
    while(remainingShots > 0) {
      TerminalMenu.displayMenu(6, numShots - remainingShots + 1);
      int x;
      int y;
      if(readInput.hasNextLine()) {
        x = readInput.nextInt();
        y = readInput.nextInt();
        if((x >= 0 && x < width)
          && (y >= 0 && y < height)) {
          takenShots.add(new Coord(x, y));
          remainingShots--;
        }
        else {
          TerminalMenu.displayCustom("Invalid Coords!");
        }
      }
    }
    return takenShots;
  }
}
