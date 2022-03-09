package mediateca.app.main;

import java.io.IOException;

import mediateca.core.Library;
import mediateca.app.MediatecaException;
import pt.tecnico.uilib.menus.Command;

/**
 * Save to file
 */
public class DoSave extends Command<Library> {

  /**
   * @param receiver
   */
  public DoSave(Library receiver) {
    super(Label.SAVE, receiver);
    addStringField("filename", Message.saveAs());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws MediatecaException {
    try {
      _receiver.saveAs(stringField("filename"));
    } catch (IOException ioe) {
      ioe.printStackTrace();
      throw new MediatecaException(ioe.getMessage());
    }
  }
}
