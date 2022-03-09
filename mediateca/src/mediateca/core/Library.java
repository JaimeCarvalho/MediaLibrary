package mediateca.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import mediateca.app.MediatecaException;
import mediateca.app.main.DayExceptions;
import mediateca.app.users.Message;
import mediateca.core.exception.BadEntrySpecificationException;

/**
 * Class that represents the library as a whole.
 */
public class Library implements java.io.Serializable {
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201901101348L;
	private int day;
	private long balance;
	private int usersCount;
	private int worksCount;
	private List<User> users;
	private List<Work> works;
	private List<Request> requests;
	
	public Library() {
		day = 0;
		balance = 0;
		usersCount = 0;
		worksCount = 0;
		users = new ArrayList<User>();
		works = new ArrayList<Work>();
		requests = new ArrayList<Request>();
	}
	
	public Library(int _day, long _balance, int _usersCount, int _worksCount,
		List<User> _users, List<Work> _works, List<Request> _requests) {
		day = _day;
		balance = _balance;
		usersCount = _usersCount;
		worksCount = _worksCount;
		users = _users;
		works = _works;
		requests = _requests;
	}

	/**
	 * Recover the previously serialized persitent state of this application.
	 * 
	 * @param filename the name of the file containing the perssitente state to
	 *                 recover
	 * 
	 * @throws IOException            if there is a reading error while processing
	 *                                the file
	 * @throws FileNotFoundException  if the file does not exist
	 * @throws ClassNotFoundException
	 */
	public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(new File(filename));
		ObjectInputStream ois = new ObjectInputStream(fis);
		Library lib = (Library) ois.readObject();
		
		day = lib.getDay();
		balance = lib.getBalance();
		usersCount = lib.getUsersCount();
		worksCount = lib.getWorksCount();
		users = lib.getUsers();
		works = lib.getWorks();
		requests = lib.getRequests();
		
		ois.close();
		fis.close();
	}
	/**
	 * Serialize the persistent state of this application into the specified file.
	 *
	 * @param filename the name of the target file
	 * @throws MissingFileAssociationException if the name of the file to store the
	 *                                         persistent is not a valid one.
	 * @throws IOException                     if some error happen during the
	 *                                         serialization of the persistent state
	 */
	public void saveAs(String filename) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(filename));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(this);
		oos.close();
		fos.close();
	}

	/**
	 * LÃª as obras para a mediateca a partir do ficheiro de texto. Nao sao lidos
	 * quaisquer outros objectos. Usa-se um hashmap para contabilizar repetiÃ§oes de
	 * obras.
	 * 
	 * @param obras nome do ficheiro que contem a descriÃ§ao textual das obras a
	 *              carregar.
	 * @throws BadEntrySpecificationException
	 * @throws IOException
	 */
	public void importFile(String obras) throws BadEntrySpecificationException, IOException {
		Parser parser = new Parser(this);
		parser.parseFile(obras);
	}
	
	public int getDay() {
		return day;
	}

	public void incrementDay(int days) throws MediatecaException {
		if(days>0)
	    	day += days;
	    else throw new MediatecaException(DayExceptions.INVALID_DAY);
		
		//se tivessemos que atualizar o estado fariamos:
		/*for(Request r: requests){
			if(r.get_isActive() && r.get_deliveryDate() < day){
				User u = users.get(r.get_userId()); u.set_state(UserState.SUSPENSO);
			}
		}*/
	}

	public long getBalance() {
		return balance;
	}

	public void addBalance(long amount) {
		balance = balance + amount;
	}

	public int getUsersCount() {
		return usersCount;
	}

	public int getWorksCount() {
		return worksCount;
	}

	public User getUser(int idUser) throws MediatecaException {
		try {
			return users.get(idUser);
		} catch (Exception e) {
			throw new MediatecaException(Message.noSuchUser(idUser));
		}
	}

	public void addUser(String name, String email) throws MediatecaException {
		validateUser(name,email);		//validar se os campos nome e email são validos
		User u = new User(name, email);
		u.setUserId(getUsersCount());
		users.add(u);
		usersCount++;
	}

	public void setUser(User user) {
		users.set(user.getIdUser(), user);
	}

	public List<User> getUsers() {
		return users;
	}

	public Work getWork(int idWork) throws MediatecaException {
		try {
			return works.get(idWork);
		} catch (Exception e) {
			throw new MediatecaException(Message.noSuchWork(idWork));
		}
	}

	public void addWork(Work work) {
		works.add(work);
		worksCount++;
	}

	public void setWork(Work work) {
		works.set(work.getIdWork(), work);
	}

	public List<Work> getWorks() {
		return works;
	}

	public int addRequest(int userId, int workId) throws MediatecaException {
		User u = getUser(userId);
		Work w = getWork(workId);
		validateRequest(w,u);
		
		int total = w.getQuantity();
		int timeToDeliver = 0;
		
		if(total == 1)
			timeToDeliver = 3;
		else if(total <= 5) {
			timeToDeliver = 8;
		}
		else timeToDeliver = 15;
		
		
		Request r = new Request(w, u, getDay() + timeToDeliver);
		requests.add(r);
		return r.getDeliveryDate();
	}
	
	private void validateRequest(Work work, User user) throws MediatecaException {
	    List<Request> requests = getRequest(user.getIdUser(), true);  
	    for(Request r: requests) {
	    	if(r.getWork().getIdWork() == work.getIdWork())
	    		throw new MediatecaException(Message.ruleFailed(1));
	    }
	    
	    if(user.getState().equals(UserState.SUSPENSO)) {
	    	throw new MediatecaException(Message.ruleFailed(2));
	    }
	    else if(getRequestCounter(work.getIdWork()) == work.getQuantity()) {
	    	throw new MediatecaException(Message.ruleFailed(3));
	    }
	    else if(getRequest(user.getIdUser(), true).size() == user.getBehaviour().getQtd()) {
	    	throw new MediatecaException(Message.ruleFailed(4));
	    }
	}
	
	//validação dos campos
	private void validateUser(String name, String email) throws MediatecaException {
		if(name.isEmpty() || email.isEmpty() || !email.contains("@") || !email.contains("."))
	    	throw new MediatecaException(Message.userRegistrationFailed(name, email));
	}
	
	public List<Request> getRequest(int idUser, boolean active) {
		List<Request> resultList = new ArrayList<Request>();
		for (Request req : requests) {
			if (req.getIsActive() == active && req.getUserId() == idUser)
				resultList.add(req);
		}
		return resultList;
	}

	public int getRequestCounter(int idWork) {
		int result = 0;
		for (Request r : requests) {
			if (r.getIsActive() && r.getWorkId() == idWork)    
				result++;										//incrementa o número de requisições
		}
		return result;
	}

	public List<Request> getRequests() {
		return requests;
	}
	
	public List<String> showRequests(int userId) {
		List<String> resultList = new ArrayList<String>();
		for(Request r: getRequest(userId, true)) {
			if (r.getWork() instanceof Book) {
				resultList.add(r.getUserId()+" - "+r.getWork().getIdWork()+" - LIVRO - "+r.getDeliveryDate());
			}
			else if (r.getWork() instanceof Dvd) {
				resultList.add(r.getUserId()+" - "+r.getWork().getIdWork()+" - DVD - "+r.getDeliveryDate());
			}
		}
		return resultList;
	}
	
	public List<String> showUsers() {
		List<String> resultList = new ArrayList<String>();
		for(User u: getUsers()) {
	    	resultList.add(u.getIdUser()+" - "+u.getName()+" - "+u.getEmail()+" - "+u.getBehaviour()+" - "+u.getState().toString()+" - EUR "+u.getBalance());
	    }
		return resultList;
	}
	
	public List<String> displayWorks(){
		List<String> resultList = new ArrayList<String>();
		for(Work w: getWorks()) {
			int total = w.getQuantity();
			int available = total - getRequestCounter(w.getIdWork());
			if(w instanceof Book) {
				Book b = (Book) w;
				resultList.add(b.getIdWork()+" - "+available+" de "+total+" - Livro  - "+b.getTitle()+" - "+b.getValue()+" - "+b.getAuthor()+" - "+b.getPublisher()+" - "+b.getISBN());
			}
			else if(w instanceof Dvd) {
				Dvd d = (Dvd) w;
				resultList.add(d.getIdWork()+" - "+available+" de "+total+" - DVD  - "+d.getTitle()+" - "+d.getValue()+" - "+d.getDirector()+" - "+d.getDuration());
				}
		}
		return resultList;
	}
	
	public List<String> performSearch(String keyword){
		List<String> resultList = new ArrayList<String>();
		for (Work w : getWorks()) {
			int total = w.getQuantity();
			int available = total - getRequestCounter(w.getIdWork());
			if (w instanceof Dvd) {
				Dvd d = (Dvd) w;
				if(d.getDirector().toLowerCase().contains(keyword) || d.getTitle().toLowerCase().contains(keyword))
					resultList.add(d.getIdWork()+" - "+available+" de "+total+" - DVD  - "+d.getTitle()+" - "+d.getValue()+" - "+d.getDirector()+" - "+d.getDuration());
			}
			else if(w instanceof Book) {
				Book b = (Book) w;
				if(b.getAuthor().toLowerCase().contains(keyword) || b.getTitle().toLowerCase().contains(keyword))
					resultList.add(b.getIdWork()+" - "+available+" de "+total+" - Livro  - "+b.getTitle()+" - "+b.getValue()+" - "+b.getAuthor()+" - "+b.getPublisher()+" - "+b.getISBN());
			}
		}
		return resultList;
	}
	
	
	public int returnWork (int userId, int workId) throws MediatecaException {
		  int delayedDays = 0;
		  int valueToPay = 0;
		  boolean found = false;
		  List<Request> activeUserRequests = getRequest(userId, true);
		  //para guardar um grupo de objectos
		  Collections.reverse(activeUserRequests);	//lista ordenada da ultima req para a primeira
		  for(Request r: activeUserRequests) {     //para cada requisição dos utilizadores activos
			  if(r.getWorkId() == workId) {
				  if (r.getDeliveryDate() >= getDay()) {		//esta dentro do prazo
					  if(r.getWork() instanceof Book) {
						  valueToPay = 2;
					  }
					  else if (r.getWork() instanceof Dvd) {
						  valueToPay = 4;
					  }
					  //ver estado de sucesso das ultimas 3
					  List<Request> nonActiveUserRequests = getRequest(userId, false);
					  Collections.reverse(nonActiveUserRequests);	//lista ordenada da ultima req para a primeira
					  int count = 1;	//a actual esta dentro do prazo
					  for(Request req: nonActiveUserRequests) {
						  if(req.getFulFilled()) {
							  count++;
							  if(count == 4) {
								  break;
							  }
						  }
						  else {
							  break;
						  }
					  }
					  if(count == 4) {
						  getUser(userId).setBehaviour(UserBehaviour.CUMPRIDOR);
					  }
					  else{
						  getUser(userId).setBehaviour(UserBehaviour.NORMAL);
					  }
				  }
				  else {	//estou fora do prazo
					  delayedDays = getDay() - r.getDeliveryDate();
						  
					  if(r.getWork() instanceof Book) {
						  valueToPay = 2 + (2 * delayedDays);
					  }
					  else if (r.getWork() instanceof Dvd) {
						  valueToPay = 4 + (2 * delayedDays);
					  }
					  getUser(userId).setBehaviour(UserBehaviour.NORMAL);
				  }
				  getUser(r.getUserId()).addBalance(valueToPay);
				  addBalance(valueToPay);
				  r.setClosed(getDay());
				  found=true;
				  break;
			  }
		  }
		  if(!found)
			  throw new MediatecaException(Message.noSuchRequest());
		  return valueToPay;
		  }

}



