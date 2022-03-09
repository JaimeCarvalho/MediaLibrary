package mediateca.app.main;

import mediateca.core.Library;
import pt.tecnico.uilib.menus.DoOpenMenu;

public class Menu extends pt.tecnico.uilib.menus.Menu {

  /**
   * @param receiver
   */
  public Menu(Library receiver) {
    super(Label.TITLE, new DoOpen(receiver), new DoSave(receiver),
          new DoDisplayDate(receiver), new DoAdvanceDate(receiver), new DoShowBalance(receiver),
          new DoOpenMenu(Label.OPEN_USERS_MENU, new mediateca.app.users.Menu(receiver)),
          new DoOpenMenu(Label.OPEN_WORKS_MENU, new mediateca.app.works.Menu(receiver)));
  }
}
