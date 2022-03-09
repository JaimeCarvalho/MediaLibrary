package mediateca.app.works;

import mediateca.core.Library;

/** 4.1. Main menu. */
public class Menu extends pt.tecnico.uilib.menus.Menu {

  /**
   * @param receiver
   */
  public Menu(Library receiver) {
    super(Label.TITLE, new DoDisplayWorks(receiver), //
          new DoPerformSearch(receiver));
  }

}
