package cs3500.pa03.view;

import java.util.ArrayList;

/**
 * class able to display a board
 */
public class TerminalBoard {
  /**
   * method to display a given board to the screen
   * @param code mode of display
   * @param board the given board or grid
   */
  public static void displayBoard(int code, ArrayList<ArrayList<String>> board) {
    if (code == 1) {
      for (ArrayList<String> list : board) {
        for (String s : list) {
          System.out.print(s + " ");
        }
        System.out.println();
      }
    }
  }
}
