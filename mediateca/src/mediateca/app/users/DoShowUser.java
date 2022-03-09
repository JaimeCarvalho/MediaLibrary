package mediateca.app.users;

import pt.tecnico.uilib.menus.Command;

import mediateca.app.MediatecaException;
import mediateca.core.Library;
import mediateca.core.User;

/**
 * Command to show a specific user.
 */
public class DoShowUser extends Command<Library> {
  /**
   * @param receiver
   */
  public DoShowUser(Library receiver) {
    super(Label.SHOW_USER, receiver);
    addIntegerField("userId", Message.requestUserId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws MediatecaException {
	  int userId = integerField("userId");
	  User u = _receiver.getUser(userId);
	  _display.popup(u.getIdUser()+" - "+u.getName()+" - "+u.getEmail()+" - "+u.getBehaviour()+" - "+u.getState().toString()+" - EUR "+u.getBalance());
  }
}
