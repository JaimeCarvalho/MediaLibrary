package mediateca.app.works;

/** Messages for menu interactions. */
@SuppressWarnings("nls")
public interface Message {

  /**
   * @return message with request for work id.
   */
  public static String requestWorkId() {
    return "Introduza o número da obra: ";
  }

  /**
   * <code>pedidoDeTermo</code> gera a mensagem que indica que é necessário
   * fornecer um termo de pesquisa ao sistema.
   * 
   * @return <code>String</code> com a mensagem
   */
  public static String requestSearchTerm() {
    return "Introduza o termo de pesquisa: ";
  }
}
