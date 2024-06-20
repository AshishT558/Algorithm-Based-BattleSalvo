package cs3500.pa03.controller;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa03.model.Coord;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManualTakeShotsTest {
  PrintStream outBackup = System.out;
  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  static Scanner readInput;
  ManualTakeShots mts;

  @BeforeEach
  public void initData() {
    System.setOut(new PrintStream(outContent));
    readInput = new Scanner(System.in);
    mts = new ManualTakeShots(readInput);
  }

  @Test
  public void testReadShots() {
    String inputs = "7 1\n"
        + "0 0\n"
        + "1 1\n"
        + "2 2\n"
        + "3 3\n";

    String revisedOut = "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Enter Shot #1 : \n" +
        "Invalid Coords!\n" +
        "Enter Shot #1 : \n" +
        "Enter Shot #2 : \n" +
        "Enter Shot #3 : \n" +
        "Enter Shot #4 : \n";

    readInput = new Scanner(inputs);
    mts = new ManualTakeShots(readInput);
    ArrayList<Coord> takenShots = mts.readShots(4, 6, 6);
    ArrayList<Coord> expectedShots = new ArrayList<>();
    expectedShots.add(new Coord(0, 0));
    expectedShots.add(new Coord(1, 1));
    expectedShots.add(new Coord(2, 2));
    expectedShots.add(new Coord(3, 3));
    String output = outContent.toString();
    assertEquals(revisedOut, output);
    assertEquals(expectedShots.size(), takenShots.size());
  }
}