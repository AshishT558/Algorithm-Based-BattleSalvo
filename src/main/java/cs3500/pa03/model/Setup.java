package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * class to setup and initalize a user's boards and ships
 */
public class Setup {
  private static int height;
  private static int width;
  private ArrayList<ArrayList<String>> board;
  private static Random randX;
  private static Random randY;

  public Setup(ArrayList<ArrayList<String>> board) {
    this.board = board;
    height = board.size();
    width = board.get(0).size();
    randX = new Random();
    randY = new Random();
  }

  public Setup(ArrayList<ArrayList<String>> board, int randSeed) {
    this.board = board;
    height = board.size();
    width = board.get(0).size();
    randX = new Random(randSeed);
    randY = new Random(randSeed);
  }

  /**
   * method that randomly generates ships on a user's board
   * @param specifications map of ships to the number of times they appear
   * @return a list of all ships of the user
   */
  public ArrayList<Ship> startBoards(Map<ShipType, Integer> specifications) {
    ArrayList<Ship> listPlacedShips = new ArrayList<>();
    for(int i = 0; i < specifications.size(); i ++) {
      switch (i) {
        case 0 -> {
          int numToPlace = specifications.get(ShipType.CARRIER);
          for(int j = 0; j < numToPlace; j++) {
            listPlacedShips.add(placeShip(ShipType.CARRIER));
          }
        }
        case 1 -> {
          int numToPlace = specifications.get(ShipType.BATTLESHIP);
          for(int j = 0; j < numToPlace; j++) {
            listPlacedShips.add(placeShip(ShipType.BATTLESHIP));
          }
        }
        case 2 -> {
          int numToPlace = specifications.get(ShipType.DESTROYER);
          for(int j = 0; j < numToPlace; j++) {
            listPlacedShips.add(placeShip(ShipType.DESTROYER));
          }
        }
        case 3 -> {
          int numToPlace = specifications.get(ShipType.SUBMARINE);
          for(int j = 0; j < numToPlace; j++) {
            listPlacedShips.add(placeShip(ShipType.SUBMARINE));
          }
        }
      }
    }
    return listPlacedShips;
  }

  /**
   * randomly places a ship onto the board
   * @param ship the type of ship being placed
   * @return the ship with its location on the board and orientation
   */
  public Ship placeShip(ShipType ship) {
    Ship placedShip = null;
    boolean placed = false;
    while(!placed) {
      int x = randX.nextInt(width);
      int y = randY.nextInt(height);
      //if randomly selected starter space is free
      if(board.get(y) != null) {
        if(board.get(y).get(x).equals("_")) {
          boolean leftValid = true;
          boolean rightValid = true;
          boolean topValid = true;
          boolean bottomValid = true;
          //check all spaces along the ship's length in the grid for four directions
          for(int i = 1; i < ship.size; i++) {
            if(x - i > 0 && x - i < width) {
              if (!board.get(y).get(x - i).equals("_")) {
                leftValid = false;
              }
            }
            else {
              leftValid = false;
            }

            if(x + i > 0 && x + i < width) {
              if (!board.get(y).get(x + i).equals("_")) {
                rightValid = false;
              }
            }
            else {
              rightValid = false;
            }

            if(y - i > 0 && y - i < height) {
              if (!board.get(y - i).get(x).equals("_")) {
                topValid = false;
              }
            }
            else {
              topValid = false;
            }

            if(y + i > 0 && y + i < height) {
              if (!board.get(y + i).get(x).equals("_")) {
                bottomValid = false;
              }
            }
            else {
              bottomValid = false;
            }
          }

          //check which direction ended up being available
          if(leftValid) {
            for(int i = 0; i < ship.size; i++) {
              board.get(y).set(x - i, "S");
            }
            placedShip = new Ship(new Coord(x, y), ship, "leftStart");
            placed = true;
          }
          else if(rightValid) {
            for(int i = 0; i < ship.size; i++) {
              board.get(y).set(x + i, "S");
            }
            placedShip = new Ship(new Coord(x, y), ship, "rightStart");
            placed = true;
          }
          else if(topValid) {
            for(int i = 0; i < ship.size; i++) {
              board.get(y - i).set(x, "S");
            }
            placedShip = new Ship(new Coord(x, y), ship, "topStart");
            placed = true;
          }
          else if(bottomValid) {
            for(int i = 0; i < ship.size; i++) {
              board.get(y + i).set(x, "S");
            }
            placedShip = new Ship(new Coord(x, y), ship, "bottomStart");
            placed = true;
          }
        }
      }

    } return placedShip;
  }

}
