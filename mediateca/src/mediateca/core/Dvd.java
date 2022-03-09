package mediateca.core;

import mediateca.app.MediatecaException;
import mediateca.app.users.Message;

public class Dvd extends Work implements java.io.Serializable{
	private static final long serialVersionUID = 6400606335453855087L;
	private String _director;
	private int _duration;
	
	//constructor
	public Dvd (int idWork, int quantity, String title, int value, String director, int duration) throws MediatecaException {
		super(idWork, quantity, title, value);
		validate(director, duration);
		_director = director;
		_duration = duration;
	}
	
	//validação dos campos
	private void validate(String director, int duration) throws MediatecaException {
		if(director.isEmpty() || duration < 0)
	    	throw new MediatecaException(Message.workRegistrationFailed());
	}

	public String getDirector() {
		return _director;
	}

	public void setDirector(String director) {
		_director = director;
	}

	public int getDuration() {
		return _duration;
	}

	public void setDuration(int duration) {
		_duration = duration;
	}

}
