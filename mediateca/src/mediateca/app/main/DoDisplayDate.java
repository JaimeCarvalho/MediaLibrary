package mediateca.app.main;

import mediateca.core.Library;
import pt.tecnico.uilib.menus.Command;

/**
 * Command to display the date.
 */
public class DoDisplayDate extends Command<Library> {

  /**
   * @param receiver
   */
  public DoDisplayDate(Library receiver) {
    super(Label.DISPLAY_DATE, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _display.popup(Message.currentDate(_receiver.getDay()));
  }
}
