package mediateca.app.works;

import java.util.List;
import mediateca.core.Library;
import pt.tecnico.uilib.menus.Command;

/**
 * Command to display all works.
 */
public class DoDisplayWorks extends Command<Library> {
  
  /** 
   * @param receiver 
   */
  public DoDisplayWorks(Library receiver) {
    super(Label.SHOW_WORKS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
	  List<String> resultList = _receiver.displayWorks();
	  for(String s: resultList) {
		  _display.addLine(s);
	  }
    _display.display();
  }
}
