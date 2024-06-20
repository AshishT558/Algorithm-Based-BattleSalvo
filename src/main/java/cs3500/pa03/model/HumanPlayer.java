package cs3500.pa03.model;

import cs3500.pa03.controller.ManualTakeShots;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * represents a human player that has manual shot selection and random ship placement
 */
public class HumanPlayer implements Player {
  public ArrayList<ArrayList<String>> board;
  public ArrayList<ArrayList<String>> opponentBoard;
  private ArrayList<Ship> listShips;
  private int remainingShips;
  private String name;

  public HumanPlayer() {
    //this.name = name;
    board = new ArrayList<>();
    opponentBoard = new ArrayList<>();
    listShips = new ArrayList<>();

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
    return listShips.size();
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
    ArrayList<Coord> allShots =
        ManualTakeShots.readShots(listShips.size(), board.size(), board.get(0).size());
    markMissed(allShots);
    return allShots;
  }

  /**
   * marks the spots on the user's opponent's board that the user has missed
   * @param allShots all shots by the user
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
   * updating the remaining ships of the user
   * @param successfulOpponentShots the shots by the user's opponent which hit the user's ships
   */
  public void updateShipCount(ArrayList<Coord> successfulOpponentShots) {
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
    }
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
