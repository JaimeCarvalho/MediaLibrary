package mediateca.app.users;

import mediateca.core.Library;
import pt.tecnico.uilib.menus.Command;
import mediateca.app.MediatecaException;

/**
 * Command to return a work.
 */
public class DoReturnWork extends Command<Library> {

  /**
   * @param receiver
   */
  public DoReturnWork(Library receiver) {
    super(Label.RETURN_WORK, receiver);
    addIntegerField("userId", Message.requestUserId());
    addIntegerField("workId", Message.requestWorkId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws MediatecaException { //atualizar valor, requests, behaviour e state
	  int userId = integerField("userId");
	  int workId = integerField("workId");
	  
	  _display.popup(Message.showAmountToPay(_receiver.returnWork(userId, workId)));
  }
}
