package mediateca.app.main;

import mediateca.app.MediatecaException;
import mediateca.core.Library;
import pt.tecnico.uilib.menus.Command;



/**
 * Command to advance the current date.
 */
public class DoAdvanceDate extends Command<Library> {

  /**
   * @param receiver
   */
  public DoAdvanceDate(Library receiver) {
    super(Label.ADVANCE_DATE, receiver);
    addIntegerField("nDays", Message.requestDaysToAdvance());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws MediatecaException{
    int days = integerField("nDays");
    _receiver.incrementDay(days);
  }

}