package cs3500.pa03.model;

/**
 * representing a grid / board coordinate
 */
public class Coord {
  public int x;
  public int y;

  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Coord)) { return false; }
    Coord that = (Coord)obj;
    return this.x == that.x
        && this.y == that.y;
  }
}
