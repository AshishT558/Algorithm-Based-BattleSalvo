package cs3500.pa03.view;

/**
 * class to display user prompts
 */
public class TerminalMenu {
  /**
   * method to display user prompts to the screen
   * @param code type of user prompt
   * @param displayNum key value relating to scenario
   */
  public static void displayMenu(int code, int displayNum) {
    switch (code) {
      case 1 -> System.out.println("Hello! Welcome to the OOD BattleSalvo Game! \n" +
          "Please enter a valid height and width - range [6, 15] - below:\n" +
          "------------------------------------------------------");
      case 2 -> System.out.println("------------------------------------------------------\n" +
          "Uh Oh! You've entered invalid dimensions. " +
          "Please remember that the height and width\n" +
          "of the game must be in the range [6, 15], inclusive. Try again!\n" +
          "------------------------------------------------------");
      case 3 -> System.out.println("Please enter your fleet in the order [Carrier, Battleship, " +
          "Destroyer, Submarine].\n" +
          "Remember, your fleet may not exceed size " + displayNum + ".\n" +
          "--------------------------------------------------------------------------------");
      case 4 -> System.out.println("-------------------------------------------------------------" +
        "-------------------\n" +
        "Uh Oh! You've entered invalid fleet sizes.\n" +
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n" +
        "Remember, your fleet may not exceed size " + displayNum + ".\nRe-enter fleet:\n" +
        "--------------------------------------------------------------------------------");
      case 5 -> System.out.println("Please Enter " + displayNum +" Shots:\n" +
          "------------------------------------------------------------------");
      case 6 -> System.out.println("Enter Shot #" + displayNum + " : ");

    }
  }

  /**
   * output a custom message to the screen
   * @param s custom message
   */
  public static void displayCustom(String s) {
    System.out.println(s);
  }
}
