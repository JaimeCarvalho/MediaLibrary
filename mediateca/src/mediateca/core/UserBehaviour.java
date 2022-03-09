package mediateca.core;

//lista de valores pré definidos
public enum UserBehaviour {
	NORMAL(3),
	CUMPRIDOR(5);
	
	private int qtd;
	
	UserBehaviour(int qtd) {
		this.qtd = qtd;
	}

	public int getQtd() {
		return qtd;
	}
}
