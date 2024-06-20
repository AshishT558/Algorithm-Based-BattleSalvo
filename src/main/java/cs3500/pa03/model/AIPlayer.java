package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * represents an ai player that has random shot generation and ship placement
 */
public class AIPlayer implements Player {
  public ArrayList<ArrayList<String>> board;
  public ArrayList<ArrayList<String>> opponentBoard;
  private ArrayList<Ship> listShips;
  private int remainingShips;
  private String name;
  private Random rand;

  public AIPlayer() {
    board = new ArrayList<>();
    opponentBoard = new ArrayList<>();
    rand = new Random();

  }

  //for testing
  public AIPlayer(int randSeed) {
    board = new ArrayList<>();
    opponentBoard = new ArrayList<>();
    rand = new Random(randSeed);

  }
  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return name;
  }

  /**
   * return the number of ships the ai player has left
   * @return remaining ships
   */
  public int numShips() {
    return remainingShips;
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public ArrayList<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    for(int i = 0; i < height; i++) {
      ArrayList<String> tempList = new ArrayList<>();
      ArrayList<String> tempList2 = new ArrayList<>();
      for(int j = 0; j < width; j++) {
        tempList.add(j, "_");
        tempList2.add(j, "_");
      }
      board.add(tempList);
      opponentBoard.add(tempList2);
    }
    Setup initShips = new Setup(board);
    listShips = initShips.startBoards(specifications);
    remainingShips = listShips.size();
    return listShips;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public ArrayList<Coord> takeShots() {
    int countToFire = remainingShips;
    ArrayList<Coord> listShots = new ArrayList<>();
    while(countToFire > 0) {
      int x = rand.nextInt(board.get(0).size());
      int y = rand.nextInt(board.size());
      if(opponentBoard.get(y).get(x).equals("_")) {
        listShots.add(new Coord(x, y));
        countToFire--;
      }
    }
    markMissed(listShots);
    return listShots;
  }

  /**
   * marks the spots on the ai's opponent's board that the ai has missed
   * @param allShots all shots by the ai
   */
  public void markMissed(ArrayList<Coord> allShots) {
    for(Coord c : allShots) {
      if(opponentBoard.get(c.y).get(c.x).equals("_")) {
        opponentBoard.get(c.y).set(c.x, "M");
      }
    }
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   * ship on this board
   */
  @Override
  public ArrayList<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    ArrayList<Coord> hitShots = new ArrayList<>();
    for(Coord c : opponentShotsOnBoard) {
      if(board.get(c.y).get(c.x).equals("S")) {
        board.get(c.y).set(c.x, "H");
        hitShots.add(c);
      }
      else {
        board.get(c.y).set(c.x, "M");
      }
    }
    updateShipCount(hitShots);
    return hitShots;
  }

  /**
   * updates the reamining ship count based on which are sunk
   * @param successfulOpponentShots the shots of the ai's opponent that have hit the ai's ships
   */
  public void updateShipCount(ArrayList<Coord> successfulOpponentShots) {
    ArrayList<Ship> updatedShipList = new ArrayList<>();
    for(Ship s : listShips) {
      ArrayList<Coord> shipSpace = s.getShipSpaceCoords();
      for(Coord c : successfulOpponentShots) {
        if(shipSpace.contains(c)) {
          s.setHitParts(s.getHitParts() + 1);
        }
      }
      if(s.getHitParts() == s.getType().size) {
        remainingShips--;
      }
      else {
        updatedShipList.add(s);
      }
    }
    listShips = updatedShipList;
    System.out.println("\nOpponent remaining ships: " + remainingShips + "\n");
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    for(Coord c : shotsThatHitOpponentShips) {
      opponentBoard.get(c.y).set(c.x, "H");
    }
  }

    /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
  }
}
