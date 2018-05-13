/*
 * CPP datastructuren opdracht 2 Steven Minken 12 mei 2018
 */

package synoniemendata;

/**
 * Klasse verantwoordelijk voor het beheer van excepties die worden opgegooid in de synoniemenapplicatie
 */
public class synoniemenException extends Exception {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Standaard constructor zonder parameters
   */
  public synoniemenException() {
    super();
  }
  
  /**
   * Constructor met een String met de foutwaarde
   * @param s String van opgegooide exceptie
   */
  public synoniemenException(String s) {
    super(s);
  }

}
