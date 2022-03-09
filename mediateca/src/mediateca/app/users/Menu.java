package mediateca.app.users;

import mediateca.core.Library;

/** Main menu. */
public class Menu extends pt.tecnico.uilib.menus.Menu {
  /**
   * @param receiver
   */
  public Menu(Library receiver) {
    super(Label.TITLE, new DoRegisterUser(receiver), new DoShowUser(receiver), //
      new DoShowUsers(receiver), new DoRequestWork(receiver), //
      new DoReturnWork(receiver), new DoShowRequests(receiver));
  }
}
