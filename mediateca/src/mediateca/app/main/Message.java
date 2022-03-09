package mediateca.app.main;

/** Messages for menu interactions. */
@SuppressWarnings("nls")
public final class Message {

  /**
   * @return string with prompt for filename to open.
   */
  public static final String openFile() {
    return "Ficheiro a abrir: ";
  }

  /**
   * @param filename
   * @return string with "file not found" message (more elaborate).
   */
  public static final String fileNotFound(String filename) {
    return "O ficheiro '" + filename + "' não existe.";
  }

  /**
   * @return string asking for a filename.
   */
  public static final String saveAs() {
    return "Guardar ficheiro como: ";
  }

 /**
   * Gera a mensagem que indica o saldo actual da mediateca.
   * 
   * @param balance é a saldo actual do sistema, tendo em conta todos os valores
   *         pagos pelos utentes da mediateca.
   * @return <code>String</code> com a mensagem a apresentar ao utilizador.
   */
  public static final String totalBalance(long balance) {
    return "Saldo actual: " + balance;
  }

  /**
   * <code>actual</code> gera a mensagem que indica a data actual ao sistema. O
   * valor da data é medido em dias e é um número inteiro.
   * 
   * @param dataActual
   *          é a data actual do sistema.
   * @return <code>String</code> com a mensagem.
   */
  public static final String currentDate(int dataActual) {
    return "Data actual: " + dataActual;
  }

  /**
   * <code>pedidoDeDias</code> gera a mensagem que indica que é necessário
   * fornecer um número de dias ao sistema, para fazer avançar a data. O valor
   * da data é medido em dias e é um número inteiro.
   * 
   * @return <code>String</code> com a mensagem
   */
  public static final String requestDaysToAdvance() {
    return "Introduza número de dias a avançar: ";
  }

  /** Prevent instantiation. */
  private Message() {
    // EMPTY
  }

}
