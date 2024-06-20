package cs3500.pa03.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleSalvoTest {
  PrintStream outBackup = System.out;
  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  static Scanner readInput;

  private BattleSalvo game;

  @BeforeEach
  public void initData() {
    System.setOut(new PrintStream(outContent));
    readInput = new Scanner(System.in);
    game = new BattleSalvo(readInput);
  }

  @AfterEach
  public void postData() throws IOException {
    System.setOut(outBackup);
    readInput.close();
  }

  @Test
  public void testStartGame() {
    String inputs = "1 1\n"
        + "6 6\n"
        + "1 2 2 2\n"
        + "1 1 1 1\n";

    String revisedOut = "Hello! Welcome to the OOD BattleSalvo Game! \n" +
        "Please enter a valid height and width - range [6, 15] - below:\n" +
        "------------------------------------------------------\n" +
        "------------------------------------------------------\n" +
        "Uh Oh! You've entered invalid dimensions. Please remember that the height and width\n" +
        "of the game must be in the range [6, 15], inclusive. Try again!\n" +
        "------------------------------------------------------\n" +
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n" +
        "Remember, your fleet may not exceed size 6.\n" +
        "--------------------------------------------------------------------------------\n" +
        "--------------------------------------------------------------------------------\n" +
        "Uh Oh! You've entered invalid fleet sizes.\n" +
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n" +
        "Remember, your fleet may not exceed size 6.\n" +
        "Re-enter fleet:\n" +
        "--------------------------------------------------------------------------------\n" +
        "Your Board:\n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "\n" +
        "Opponent Board:\n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "end\n";

    readInput = new Scanner(inputs);
    game = new BattleSalvo(readInput);
    game.startGame(true);
    String output = outContent.toString();
    assertEquals(revisedOut, output);
  }


  @Test
  public void testEndGame() {

    String revisedOut = "YOU WIN!\n" + "YOU LOSE\n" + "DRAW\n";

    game = new BattleSalvo(readInput);
    game.postGame("user");
    game.postGame("ai");
    game.postGame("neither");

    String output = outContent.toString();
    assertEquals(revisedOut, output);
  }

}