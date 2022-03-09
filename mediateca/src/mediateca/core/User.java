package mediateca.core;

import mediateca.app.MediatecaException;

public class User implements java.io.Serializable{
	private static final long serialVersionUID = 954676050167153715L;
	private int _idUser;
	private String _name;
	private String _email;
	private UserState _state;
	private UserBehaviour _behaviour;
	private long _balance;
	
	//construtor
	public User(String name, String email) throws MediatecaException {
		_name = name;
		_email = email;
		_state = UserState.ACTIVO;
		_behaviour = UserBehaviour.NORMAL;
		_balance = 0;
	}

	public int getIdUser() {
		return _idUser;
	}
	
	public void setUserId(int userId) {
		_idUser = userId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public UserState getState() {
		return _state;      //Activo ou suspenso
	}

	public void setState(UserState state) {
		_state = state;
	}
	
	public UserBehaviour getBehaviour() {
		return _behaviour;      //Normal ou cumpridor 
	}

	public void setBehaviour(UserBehaviour behaviour) {
		_behaviour = behaviour;
	}

	public long getBalance() {
		return _balance;
	}

	public void addBalance(long balance) {
		_balance = _balance + balance;
	}
}
