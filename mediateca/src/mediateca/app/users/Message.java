package mediateca.app.users;

/** Messages for menu interactions. */
@SuppressWarnings("nls")
public interface Message {

  /**
   * <code>pedidoDeNúmero</code> gera a mensagem que indica que é necessário
   * fornecer um número de utente ao sistema.
   * 
   * @return <code>String</code> com a mensagem
   */
  static String requestUserId() {
    return "Introduza o número de utente: ";
  }

  /**
   * <code>pedidoDeNome</code> gera a mensagem que indica que é necessário
   * fornecer um nome de utente ao sistema.
   * 
   * @return <code>String</code> com a mensagem
   */
  static String requestUserName() {
    return "Introduza o nome do utente: ";
  }

  /**
   * <code>pedidoDeEmail</code> gera a mensagem que indica que é necessário
   * fornecer um endereço de correio electrónico de um utente ao sistema.
   * 
   * @return <code>String</code> com a mensagem
   */
  static String requestUserEMail() {
    return "Introduza o endereço de correio do utente: ";
  }

  /**
   * Generates message stating user registration failure.
   * 
   * @param name
   * @param email
   * @return message
   */
  static String userRegistrationFailed(String name, String email) {
    return "User registration failed: name '" + name + "', email '" + email + "'.";
  }

  /**
   * <code>inexistente</code> gera a mensagem que indica que um um utente não
   * existe.
   * 
   * @param idUser
   *          é o identificador do utente (<code>int</code>)
   * @return <code>String</code> com a mensagem
   */
  public static String noSuchUser(int idUser) {
    return "O utente " + idUser + " não existe.";
  }

  /**
   * <code>devoluçãoDia</code> gera a mensagem que comunica o dia de devolução
   * de uma obra.
   * 
   * @param idObra
   *          é o identificador da obra (<code>int</code>)
   * @param dia
   *          é o dia de devolução (<code>int</code>)
   * @return <code>String</code> com a mensagem
   */
  static String workReturnDay(int idObra, int dia) {
    return "A obra " + idObra + " deve ser devolvida no dia " + dia + ".";
  }

  /**
   * Este métodogera a mensagem que indica que uma obra não
   * existe.
   * 
   * @param idObra
   *          é o identificador da obra (<code>int</code>)
   * @return <code>String</code> com a mensagem
   */
  static String noSuchWork(int idObra) {
    return "A obra " + idObra + " não existe.";
  }

  /**
   * Este método gera a mensagem que indica que o utente não requisitou uma dada obra.
   * 
   * @return <code>String</code> com a mensagem
   */
  static String noSuchRequestk() {
    return "O utente não requisitou a obra indicada.";
  }

  /**
   * Ggera a mensagem que indica que um utente não
   * pode requisitar uma dada obra por violação de uma das regras de validação.
   * 
   * @param ixRule
   *          é o identificador da regra de validação (<code>int</code>)
   * @return <code>String</code> com a mensagem
   */
  static String ruleFailed(int ixRule) {
    return "Não pode requisitar a obra em causa. Violação da regra " + ixRule + ".";
  }

  /**
   * Gera a mensagem que indica o valor em euros a pagar pela devolução de uma obra.
   * 
   * @param euros
   *          an <code>int</code> value
   * @return <code>String</code> com a mensagem
   */
  static String showAmountToPay(int euros) {
    return "O utente tem que pagar " + euros + " EUR";
  }

  /**
   * Gera a mensagem que indica que uma
   * obra não foi requisitada por um utente.
   * 
   * @param idWork
   *          é o identificador da obra (<code>int</code>)
   * @param idUser
   *          é o identificador do utente (<code>int</code>)
   * @return <code>String</code> com a mensagem
   */
  public static String noSuchRequest() {
    return "A obra não requisitada pelo utente";
  }

  /**
   * @return message with request for work id.
   */
  public static String requestWorkId() {
    return "Introduza o número da obra: ";
  }
  
  /**
   * Generates message stating work registration failure.
   * 
   * @param name
   * @param email
   * @return message
   */
  static String workRegistrationFailed() {
    return "Work registration failed.";
  }
}
