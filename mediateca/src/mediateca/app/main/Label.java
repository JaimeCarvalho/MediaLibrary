package mediateca.app.main;

/** Menu entries for the main menu. */
@SuppressWarnings("nls")
public interface Label {

  /** Menu title. */
  String TITLE = "Menu Principal";

  /** §3.3.1. Recover state. */
  String OPEN = "Abrir dados";

  /** §3.3.1. Save state. */
  String SAVE = "Guardar dados";

  /** §3.3.2. Display date. */
  String DISPLAY_DATE = "Ver data";

  /** §3.3.3. Advance date. */
  String ADVANCE_DATE = "Avançar data";

  /** §3.3.4. Show Balance. */
  String SHOW_BALANCE = "Mostrar saldo";

  /** §3.3.5. Open users menu. */
  String OPEN_USERS_MENU = "Menu de gestão de utentes";

  /** §3.3.5. Open works menu. */
  String OPEN_WORKS_MENU = "Menu de gestão de obras";
}
