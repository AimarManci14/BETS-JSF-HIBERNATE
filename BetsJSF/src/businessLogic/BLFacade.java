package businessLogic;

import java.util.ArrayList;
import java.util.Date;





//import domain.Booking;
import domain.Question;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * Interface that specifies the business logic.
 */

public interface BLFacade  {
	  

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
	Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public ArrayList<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public ArrayList<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeBD();

	public String createUser(String userName, String password, String name, String lastName,	String email);
	
	public String existUser(String username, String password);
	
	public String insertMoney(String username, double money);

	public String removeEvent(Event event);
	
	public String createEvent(String description, Integer year, Integer month, Integer day);

}
