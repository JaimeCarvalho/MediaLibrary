package mediateca.core;

import mediateca.app.MediatecaException;

public class Request implements java.io.Serializable{
	private static final long serialVersionUID = 3532578717191509042L;
	private Work _work;
	private User _user;
	private int _deliveryDate;
	private boolean _fulfilled;
	private boolean _isActive;
	
	//construtor
	public Request(Work work, User user, int deliveryDate) throws MediatecaException{ 
		_user = user;
		_work = work;
		_deliveryDate = deliveryDate;
		_isActive = true;
	}

	public Work getWork() {
		return _work;
	}

	public void setWork(Work work) {
		_work = work;
	}
	
	public int getWorkId() {
		return _work.getIdWork();
	}

	public User getUser() {
		return _user;
	}
	
	public int getUserId() {
		return _user.getIdUser();
	}

	public void setUser(User user) {
		_user = user;
	}

	public int getDeliveryDate() {
		return _deliveryDate;
	}

	public void setDeliveryDate(int deliveryDate) {
		_deliveryDate = deliveryDate;
	}
	
	public boolean getIsActive() {
		return _isActive;
	}

	public void setClosed(int currentDay) {
		_isActive = false;
		_fulfilled = currentDay <= _deliveryDate;    //condição para utilizador Cumpridor
	}
	
	public boolean getFulFilled() {
		return _fulfilled;      //devolver os utilizadores que cumpriram com as últimas 4 entregas
	}
	
}
