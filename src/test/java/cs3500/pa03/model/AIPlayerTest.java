package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AIPlayerTest {

  private static AIPlayer testAI;

  @BeforeEach
  public void initAIPlayer() {
    testAI = new AIPlayer(5);

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

    ArrayList<Ship> actualShips = testAI.setup(1, 6, testSpecs);
    assertTrue(testShipsExpected.contains(actualShips.get(0)));
  }

  @Test
  public void testTakeShots() {
    HashMap<ShipType, Integer> testSpecs = new HashMap<>();
    testSpecs.put(ShipType.CARRIER, 1);
    testAI.setup(1, 6, testSpecs);

    ArrayList<Coord> shotsActual = testAI.takeShots();
    //seed(5) generated random numbers start in order 5,0
    assertEquals(new Coord(5, 0), shotsActual.get(0));
  }

  @Test
  public void testReportDamage() {
    //taking 7 shots at a 1x6 board with a carrier, 6 shots are guaranteed to hit
    HashMap<ShipType, Integer> testSpecs = new HashMap<>();
    testSpecs.put(ShipType.CARRIER, 1);
    testAI.setup(1, 7, testSpecs);
    ArrayList<Coord> opponentShots = new ArrayList<>();
    opponentShots.add(new Coord(0, 0));
    opponentShots.add(new Coord(1, 0));
    opponentShots.add(new Coord(2, 0));
    opponentShots.add(new Coord(3, 0));
    opponentShots.add(new Coord(4, 0));
    opponentShots.add(new Coord(5, 0));
    opponentShots.add(new Coord(6, 0));
    ArrayList<Coord> hitShots = testAI.reportDamage(opponentShots);
    assertEquals(6, hitShots.size());

  }

  @Test
  public void testSuccessfulHits() {
    HashMap<ShipType, Integer> testSpecs = new HashMap<>();
    testSpecs.put(ShipType.CARRIER, 1);
    testAI.setup(1, 6, testSpecs);

    assertEquals("_", testAI.opponentBoard.get(0).get(0));
    ArrayList<Coord> successfulShots = new ArrayList<>();
    successfulShots.add(new Coord(0,0));
    testAI.successfulHits(successfulShots);
    assertEquals("H", testAI.opponentBoard.get(0).get(0));
  }


}