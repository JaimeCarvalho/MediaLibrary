package mediateca.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import mediateca.core.Library;
import mediateca.app.MediatecaException;
import pt.tecnico.uilib.menus.Command;

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<Library> {

  /**
   * @param receiver
   */
  public DoOpen(Library receiver) {
    super(Label.OPEN, receiver);
    addStringField("filename", Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws MediatecaException {
    try {
      _receiver.load(stringField("filename"));
    }  catch (FileNotFoundException fnfe) {
      throw new MediatecaException(Message.fileNotFound(stringField("filename")));
    } catch (ClassNotFoundException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw new MediatecaException(e.getMessage());
    }
  }
}
