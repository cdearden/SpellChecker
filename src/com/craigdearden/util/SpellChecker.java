
package com.craigdearden.util;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SpellChecker {

  /**
   * Checks the spelling of a word against an online database of words.
   *
   * @param word the word to check for correct spelling.
   * @return true if the word is spelled correctly, false otherwise.
   * @throws IllegalArgumentException if the word contains any characters
   * other than alphabetic characters, "'", or "-"
   * @throws NullPointerException if word is null.
   * @throws java.io.IOException if the method was unable to connect to the
   * online database.
   */
  public static boolean check(String word) throws IllegalArgumentException, NullPointerException,
      IOException {
    if (word == null || !word.matches("(?!'-)\\p{Alpha}+")) {
      throw new IllegalArgumentException();
    }

    boolean correct = false;
    try {
      correct = databaseContains(word);
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }

    return correct;
  }

  /**
   * Connects to http://wordnetweb.princeton.edu and determines
   * if the word is not in their database. If it is not, the page
   * displays "Your search did not return any results."
   *
   * @param word the word to check against the database.
   * @return true if the page did not display "Your search did not return any results."
   * @throws IOException if Jsoup fails to connect to the web.
   */
  private static boolean databaseContains(String word) throws IOException {
    String url = "http://wordnetweb.princeton.edu/perl/webwn?s=" + word;

    Document doc = null;
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException e) {
      throw new IOException("Failed to connect to database.");
    }

    return !(doc.toString().contains("Your search did not return any results."));
  }
}
