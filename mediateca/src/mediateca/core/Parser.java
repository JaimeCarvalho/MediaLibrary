package mediateca.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import mediateca.app.MediatecaException;
import mediateca.core.exception.BadEntrySpecificationException;

public class Parser {
	private Library _library;

  Parser(Library library) {
    _library = library;
  }

  //ler linha a linha do ficheiro
  void parseFile(String fileName) throws IOException, BadEntrySpecificationException {

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
      String line;
      
      while ((line = reader.readLine()) != null)    //enquanto o ficheiro contiver linhas para ler
        parseLine(line);
    } 
  }

  //verificação da primeira posição de cada linha do ficheiro
  private void parseLine(String line) throws BadEntrySpecificationException {
    String[] components = line.split(":");

    switch (components[0]) {
      case "DVD":
        parseDVD(components, line);
        break;

      case "BOOK":
        parseBook(components, line);
        break;

      case "USER":
        parseUser(components, line);
        break;

      default:
        throw new BadEntrySpecificationException("Line with wong type: " + components[0]);
    }
  }
  
  //caso seja DVD lê no formato pretendido
  private void parseDVD(String[] components, String line) throws BadEntrySpecificationException {
    if (components.length != 6)
      throw new BadEntrySpecificationException("Invalid number of fields (6) in DVD description: " + line);
    
    String director = components[2];
    String title = components[1];
    int price = Integer.parseInt(components[3]);
    int copies = Integer.parseInt(components[4]);
    int duration = Integer.parseInt(components[5]);
    
    try {
		Dvd d = new Dvd(_library.getWorksCount(), copies, title, price, director, duration);
		_library.addWork(d);
	} catch (MediatecaException e) {
		System.out.println(e.getMessage());
	}
  }

  //caso seja Book lê no formato pretendido
  private void parseBook(String[] components, String line) throws BadEntrySpecificationException {
    if (components.length != 7)
    	throw new BadEntrySpecificationException("Invalid number of fields (6) in book description: " + line);
    
    String author = components[2];
    String isbn = components[5];
    String title = components[1];
    int price = Integer.parseInt(components[3]);
    String publisher = components[4];
    int copies = Integer.parseInt(components[6]);

    try {
		Book b = new Book(_library.getWorksCount(), copies, title, price, author, publisher, isbn);
		_library.addWork(b);
	} catch (MediatecaException e) {
		System.out.println(e.getMessage());
	}
 }

  //caso seja User lê no formato pretendido
  private void parseUser(String[] components, String line) throws BadEntrySpecificationException {
    if (components.length != 3)
      throw new BadEntrySpecificationException("Invalid number of fields (2) in user description: " + line);
    
    String name = components[1];
    String email = components[2];
    
    try {
    	_library.addUser(name,email);
	} catch (MediatecaException e) {
		System.out.println(e.getMessage());
	}
  }

}
