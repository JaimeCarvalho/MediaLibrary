package mediateca.app.users;

import mediateca.core.Library;
import pt.tecnico.uilib.menus.Command;
import mediateca.app.MediatecaException;

/**
 * Command to register a new user.
 */
public class DoRegisterUser extends Command<Library> {

  /**
   * @param receiver
   */
  public DoRegisterUser(Library receiver) {
    super(Label.REGISTER_USER, receiver);
    addStringField("name", Message.requestUserName());
    addStringField("email", Message.requestUserEMail());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws MediatecaException {
	  String name = stringField("name");
	  String email = stringField("email");
    
	  //utentecount é o contador de utentes existente
	  _receiver.addUser(name, email); //falta-nos fazer display popup
   
  }
}
