package mediateca.app.users;

import mediateca.core.Library;
import pt.tecnico.uilib.menus.Command;
import mediateca.app.MediatecaException;


/**
 * Command to request a work.
 */
public class DoRequestWork extends Command<Library> {

  /**
   * @param receiver
   */
  public DoRequestWork(Library receiver) {
    super(Label.REQUEST_WORK, receiver);
    addIntegerField("userId", Message.requestUserId());
    addIntegerField("workId", Message.requestWorkId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws MediatecaException {
	int userId = integerField("userId");
    int workId = integerField("workId");
    
    int deliveryDate =_receiver.addRequest(userId, workId);
	//disponibiliza uma mensagem de sucesso devolvendo o id da obra e a data de entrega da mesma
    _display.popup(Message.workReturnDay(workId, deliveryDate));
  }
}
