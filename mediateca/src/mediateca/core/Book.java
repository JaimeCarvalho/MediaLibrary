package mediateca.core;

import mediateca.app.MediatecaException;
import mediateca.app.users.Message;

public class Book extends Work implements java.io.Serializable{
	private static final long serialVersionUID = 7343416587996792262L;
	private String _author;
	private String _publisher;
	private String _ISBN; //tem que ser verificada
	private final static int ISBN_LENGTH = 10;
	
	//construtor
	public Book(int idWork, int quantity, String title, int value, String author, String publisher, String ISBN) throws MediatecaException {
		super(idWork, quantity, title, value);
		validate(author, publisher, ISBN);
		_author = author;
		_publisher = publisher;
		_ISBN = ISBN;
	}
	
	//validação dos campos
	private void validate(String author, String publisher, String ISBN) throws MediatecaException {
		if(author.isEmpty() || publisher.isEmpty() || ISBN.length() != ISBN_LENGTH)
	    	throw new MediatecaException(Message.workRegistrationFailed());
	}
	
	public String getAuthor() {
		return _author;
	}
	
	public void setAuthor(String author) {
		_author = author;
	}

	public String getPublisher() {
		return _publisher;
	}

	public void setPublisher(String publisher) {
		_publisher = publisher;
	}

	public String getISBN() {
		return _ISBN;
	}

	public void setISBN(String ISBN) {
		_ISBN = ISBN;
	}

}
