package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetupTest {
  private static Setup testStart;

  @BeforeEach
  public void initHumanPlayer() {
    ArrayList<ArrayList<String>> testBoard = new ArrayList<>();
    for(int i = 0; i < 6; i++) {
      ArrayList<String> tempList = new ArrayList<>();
      ArrayList<String> tempList2 = new ArrayList<>();
      for(int j = 0; j < 6; j++) {
        tempList.add(j, "_");
      }
      testBoard.add(tempList);
    }
    testStart = new Setup(testBoard, 5);
  }

  @Test
  public void testStartBoards() {
    HashMap<ShipType, Integer> testSpecs = new HashMap<>();
    testSpecs.put(ShipType.CARRIER, 1);
    testSpecs.put(ShipType.BATTLESHIP, 1);
    testSpecs.put(ShipType.DESTROYER, 1);
    testSpecs.put(ShipType.SUBMARINE, 1);
    ArrayList<Ship> placedShips = testStart.startBoards(testSpecs);
    assertEquals(4, placedShips.size());
  }

  @Test
  public void testPlaceShips() {
    ArrayList<Ship> placedShips = new ArrayList<>();
    assertEquals(0, placedShips.size());
    placedShips.add(testStart.placeShip(ShipType.CARRIER));
    assertEquals(1, placedShips.size());
    placedShips.add(testStart.placeShip(ShipType.BATTLESHIP));
    assertEquals(2, placedShips.size());
    placedShips.add(testStart.placeShip(ShipType.DESTROYER));
    assertEquals(3, placedShips.size());
    placedShips.add(testStart.placeShip(ShipType.SUBMARINE));
    assertEquals(4, placedShips.size());

  }

}