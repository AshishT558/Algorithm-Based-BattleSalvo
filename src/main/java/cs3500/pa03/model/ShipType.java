package cs3500.pa03.model;

/**
 * to represent different types of BattleSalvo ships:
 * carriers, battleships, destroyers, and subs
 */
public enum ShipType {
  CARRIER("Carrier", 6),
  BATTLESHIP("Battleship", 5),
  DESTROYER("Destroyer", 4),
  SUBMARINE("Submarine", 3);

  public final String name;
  public final int size;

  ShipType(String name, int size) {
    this.name = name;
    this.size = size;
  }
}
