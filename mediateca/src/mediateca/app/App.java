package mediateca.app;

import static pt.tecnico.uilib.Dialog.UI;
import mediateca.core.Library;
import mediateca.core.exception.BadEntrySpecificationException;
import java.io.IOException;
/**
 * Main driver for the travel management application.
 */
public class App {
	/**
   * @param args
   */
  public static void main(String[] args) {
	  //criar um objecto do tipo library com os diversos campos
	Library lib = new Library();

    String datafile = System.getProperty("import"); //$NON-NLS-1$
    if (datafile != null) {
      try {
        lib.importFile(datafile);
      } catch (BadEntrySpecificationException | IOException e) {
        // file input should always be correct: just present the problem
        // no behavior described: just present the problem
        System.err.println("Error in parsing: " + e.getMessage());
        e.printStackTrace();
      }
    }
    try {
      new mediateca.app.main.Menu(lib).open();
    } finally {
      UI.close();
    }
  }
}
