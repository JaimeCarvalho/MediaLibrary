package mediateca.app;

import pt.tecnico.uilib.menus.CommandException;

/**
 * Represents the exception for all commands of this application
 */
public class MediatecaException extends CommandException {  
  public MediatecaException(String message) {
    super(message);
  }
}
