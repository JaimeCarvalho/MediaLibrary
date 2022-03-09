package mediateca.core;

import mediateca.app.MediatecaException;
import mediateca.app.users.Message;

public class Work implements java.io.Serializable{
	private static final long serialVersionUID = 5193906182148749365L;
	private int _idWork = 0;
	private int _quantity;
	private String _title;
	private int _value;

	//construtor
	public Work(int idWork, int quantity, String title, int value) throws MediatecaException {
		validate(quantity, title, value);
		_idWork = idWork; 
		_quantity = quantity;
		_title = title;
		_value = value;
	}
	
	private void validate(int quantity, String title, int value) throws MediatecaException {
		if(quantity < 0 || title.isEmpty() || value < 0)
	    	throw new MediatecaException(Message.workRegistrationFailed());
	}

	public int getIdWork() {
		return _idWork;
	}

	public int getQuantity() {
		return _quantity;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public int getValue() {
		return _value;
	}

	public void setValue(int value) {
		_value = value;
	}
}
