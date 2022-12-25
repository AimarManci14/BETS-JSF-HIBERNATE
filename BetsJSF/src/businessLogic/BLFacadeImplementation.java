package businessLogic;
//hola
import java.util.Date;
import java.util.ResourceBundle;
import java.util.ArrayList;


import dataAccess.DataAccessInterface;
import domain.Question;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
public class BLFacadeImplementation  implements BLFacade {
	DataAccessInterface dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
	}
	
    public BLFacadeImplementation(DataAccessInterface da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
			da.initializeDB();


		
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
		Question qry=null;
		
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished("ErrorEventHasFinished");
				
		 qry=dbManager.createQuestion(event,question,betMinimum);		
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    	
	public ArrayList<Event> getEvents(Date date)  {
		ArrayList<Event>  events=dbManager.getEvents(date);
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public ArrayList<Date> getEventsMonth(Date date) {
		ArrayList<Date>  dates=dbManager.getEventsMonth(date);
		return dates;
	}
	
	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    	
	 public void initializeBD(){
		dbManager.initializeDB();
	}

	 public String createUser(String userName, String password, String name, String lastName,	String email) {
		 String ok = dbManager.createUser(userName, password, name, lastName, email);
		 return ok;
	 }
	 public String existUser(String username, String password) {
		 String ok = dbManager.existUser(username, password);
		 return ok;
	 }
	 
	 public String insertMoney(String username, double money) {
		 String ok = dbManager.insertMoney(username, money);
		 return ok;
	 }

	 public String removeEvent(Event event) {
		 String ok = dbManager.removeEvent(event);
		 return ok;
	 }
	 
	 public String createEvent(String description, Integer year, Integer month, Integer day) {
		 String ok = dbManager.createEvent(description, year, month, day);
		 return ok;
	 }

}

