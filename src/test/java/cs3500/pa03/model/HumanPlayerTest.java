package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HumanPlayerTest {

  private static HumanPlayer testUser;

  @BeforeEach
  public void initHumanPlayer() {
    testUser = new HumanPlayer();
  }
  @Test
  public void testSetup() {
    HashMap<ShipType, Integer> testSpecs = new HashMap<>();
    testSpecs.put(ShipType.CARRIER, 1);

    Ship testCarrierLeftStart = new Ship(new Coord(0,0), ShipType.CARRIER, "rightStart");
    Ship testCarrierRightStart = new Ship(new Coord(5,0), ShipType.CARRIER, "leftStart");
    ArrayList<Ship> testShipsExpected = new ArrayList<>();
    testShipsExpected.add(testCarrierLeftStart);
    testShipsExpected.add(testCarrierRightStart);

    ArrayList<Ship> actualShips = testUser.setup(1, 6, testSpecs);
    assertTrue(testShipsExpected.contains(actualShips.get(0)));
  }

//  @Test
//  public void testTakeShots() {
//    HashMap<ShipType, Integer> testSpecs = new HashMap<>();
//    testSpecs.put(ShipType.CARRIER, 1);
//    testUser.setup(1, 7, testSpecs);
//    assertEquals(1, testUser.takeShots().size());
//  }

  @Test
  public void testMarkMissed() {
    HashMap<ShipType, Integer> testSpecs = new HashMap<>();
    testSpecs.put(ShipType.CARRIER, 1);
    testUser.setup(1, 6, testSpecs);
    assertEquals("_", testUser.opponentBoard.get(0).get(0));
    ArrayList<Coord> allShotsTaken = new ArrayList<>();
    allShotsTaken.add(new Coord(0, 0));
    testUser.markMissed(allShotsTaken);
    assertEquals("M", testUser.opponentBoard.get(0).get(0));

  }


  @Test
  public void testReportDamage() {
    HashMap<ShipType, Integer> testSpecs = new HashMap<>();
    testSpecs.put(ShipType.CARRIER, 1);
    testUser.setup(1, 7, testSpecs);
    ArrayList<Coord> opponentShots = new ArrayList<>();
    opponentShots.add(new Coord(0, 0));
    opponentShots.add(new Coord(1, 0));
    opponentShots.add(new Coord(2, 0));
    opponentShots.add(new Coord(3, 0));
    opponentShots.add(new Coord(4, 0));
    opponentShots.add(new Coord(5, 0));
    opponentShots.add(new Coord(6, 0));
    ArrayList<Coord> hitShots = testUser.reportDamage(opponentShots);
    assertEquals(6, hitShots.size());

  }

  @Test
  public void testUpdateShipCount() {

  }

  @Test
  public void testSuccessfulHits() {
    HashMap<ShipType, Integer> testSpecs = new HashMap<>();
    testSpecs.put(ShipType.CARRIER, 1);
    testUser.setup(1, 6, testSpecs);

    assertEquals("_", testUser.opponentBoard.get(0).get(0));
    ArrayList<Coord> successfulShots = new ArrayList<>();
    successfulShots.add(new Coord(0,0));
    testUser.successfulHits(successfulShots);
    assertEquals("H", testUser.opponentBoard.get(0).get(0));
  }


}