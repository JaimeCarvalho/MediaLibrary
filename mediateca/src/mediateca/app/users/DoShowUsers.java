package mediateca.app.users;

import java.util.List;

import mediateca.core.Library;
import pt.tecnico.uilib.menus.Command;

/**
 * Command to show all users.
 */
public class DoShowUsers extends Command<Library> {

  /**
   * @param receiver
   */
  public DoShowUsers(Library receiver) {
    super(Label.SHOW_USERS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
	  List<String> resultList = _receiver.showUsers();
	  for(String s: resultList) {
		  _display.addLine(s);
	  }
    _display.display();
  }
}
