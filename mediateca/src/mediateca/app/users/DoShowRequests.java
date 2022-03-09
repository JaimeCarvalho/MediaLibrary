package mediateca.app.users;

import pt.tecnico.uilib.menus.Command;
import mediateca.core.Library;
import java.util.List;
import mediateca.app.MediatecaException;

/**
 * Command to show all users.
 */
public class DoShowRequests extends Command<Library> {
  /**
   * @param receiver
   */
  public DoShowRequests(Library receiver) {
    super(Label.SHOW_USER_REQUESTS, receiver);
    addIntegerField("userId", Message.requestUserId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws MediatecaException {
	  int userId = integerField("userId");
	  List<String> resultList = _receiver.showRequests(userId);
	  for(String s: resultList) {
		  _display.addLine(s);
	  }
    _display.display();
  }
}