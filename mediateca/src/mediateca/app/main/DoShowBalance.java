package mediateca.app.main;

import mediateca.core.Library;
import pt.tecnico.uilib.menus.Command;


/**
 * Command to display the date.
 */
public class DoShowBalance extends Command<Library> {

  /**
   * @param receiver
   */
  public DoShowBalance(Library receiver) {
    super(Label.SHOW_BALANCE, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
	_display.popup(Message.totalBalance(_receiver.getBalance()));
  }
}
