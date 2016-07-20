
package com.craigdearden.util;

import java.io.IOException;
import java.util.Scanner;

public class SpellCheckerTest {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    boolean exit = false;
    do {
      try {
        System.out.println("Enter a word.");
        String input = sc.nextLine();
        if (input.equals("999")) {
          exit = true;
        } else {
          System.out.printf("%s%n", (SpellChecker.check(input) ? "CORRECT" : "INCORRECT"));
        }
      } catch (NullPointerException | IllegalArgumentException e) {
        System.out.println("Invalid input! Must enter a word.");
        sc.nextLine();
      } catch (IOException e) {
        System.out.println(e.getMessage());
        exit = true;
      }
    } while (!exit);
  }

}
