package cs3500.pa03.view;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa03.controller.BattleSalvo;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TerminalMenuTest {
  PrintStream outBackup = System.out;
  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  static Scanner readInput;

  @BeforeEach
  public void initData() {
    System.setOut(new PrintStream(outContent));
    readInput = new Scanner(System.in);
  }

  @Test
  public void testShotDisplay() {
    TerminalMenu.displayMenu(5, 6);
    TerminalMenu.displayMenu(6, 6);
    String expectedOut = "Please Enter 6 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Enter Shot #6 : \n";

    String output = outContent.toString();
    assertEquals(expectedOut, output);
    readInput.close();
  }
}