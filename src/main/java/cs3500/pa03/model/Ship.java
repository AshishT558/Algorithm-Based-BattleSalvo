package cs3500.pa03.model;

import java.util.ArrayList;

public class Ship {
  private final Coord startLocation;
  private final ShipType shipType;
  private final String direction;

  private int partsOfShipHit;
  public Ship(Coord startLocation, ShipType shipType, String direction) {
    this.startLocation = startLocation;
    this.shipType = shipType;
    this.direction = direction;
    this.partsOfShipHit = 0;
  }

  public Coord getLoc() {
    return startLocation;
  }

  public ShipType getType() {
    return shipType;
  }
  public String getDir() {
    return direction;
  }
  public int getHitParts() {
    return partsOfShipHit;
  }
  public void setHitParts(int i) {
    if(i > 0 && i <= shipType.size) {
      partsOfShipHit = i;
    }
  }

  public ArrayList<Coord> getShipSpaceCoords() {
    ArrayList<Coord> spaceTakenByShip = new ArrayList<>();
    int dx = 0;
    int dy = 0;
    switch (direction) {
      case "leftStart" -> dx = -1;
      case "rightStart" -> dx = 1;
      case "topStart" -> dy = -1;
      case "bottomStart" -> dy = 1;
    }

    for(int i = 0; i < shipType.size; i++) {
      int newXSpace = getLoc().x + (i * dx);
      int newYSpace = getLoc().y + (i * dy);
      spaceTakenByShip.add(new Coord(newXSpace, newYSpace));
    }
    return spaceTakenByShip;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Ship)) { return false; }
    Ship that = (Ship)obj;
    return this.startLocation.equals(that.startLocation)
        && this.shipType.size == that.shipType.size
        && this.shipType.name.equals(that.shipType.name)
        && this.direction.equals(that.direction);
  }
}
