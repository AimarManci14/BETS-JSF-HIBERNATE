package dataAccess;

import java.io.File;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import eredua.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import java.util.*;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess implements DataAccessInterface {
     public DataAccess()  {
	}
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event("Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event("Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event("Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event("Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event("Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event("Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event("Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event("Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event("Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event("Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event("Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event("Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event("Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event("Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event("Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event("Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event("Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event("Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event("Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event("Betis-Real Madrid", UtilDate.newDate(year,month,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					

				ev1.addQuestion("Who wins the game?",1);
				ev1.addQuestion("Who scores first?",2);
				ev11.addQuestion("Who wins the game?",1);
				ev11.addQuestion("How many goals will be scored?",2);
				ev17.addQuestion("Who wins the game?",1);
				ev17.addQuestion("How many goals will be scored in the first half?",2);
				
	
	        User u = new User ("admin","123","admin","admin","admin@gmail.com");
	        session.persist(u);
			session.persist(ev1);
			session.persist(ev2);
			session.persist(ev3);
			session.persist(ev4);
			session.persist(ev5);
			session.persist(ev6);
			session.persist(ev7);
			session.persist(ev8);
			session.persist(ev9);
			session.persist(ev10);
			session.persist(ev11);
			session.persist(ev12);
			session.persist(ev13);
			session.persist(ev14);
			session.persist(ev15);
			session.persist(ev16);
			session.persist(ev17);
			session.persist(ev18);
			session.persist(ev19);
			session.persist(ev20);			
			
			session.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
			Event ev =(Event) session.load(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist("ErrorQueryAlreadyExist");
			 Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			session.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			session.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public ArrayList<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		ArrayList<Event> res = new ArrayList<Event>();	
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Event WHERE eventDate= :date");   
		query.setParameter("date", date);
		List<Event> events = query.list();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	session.getTransaction().commit();

	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public ArrayList<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		ArrayList<Date> res = new ArrayList<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Event WHERE eventDate BETWEEN :1 and :2");   
		query.setParameter("1", firstDayMonthDate);
		query.setParameter("2", lastDayMonthDate);
		List<Date> dates = query.list();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	session.getTransaction().commit();
	 	return res;
	}
	
public boolean existQuestion(Event event, String question) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev =(Event) session.load(Event.class, event.getEventNumber());
	boolean exists = ev.DoesQuestionExists(question);
	session.getTransaction().commit();
	return exists;
	
}

public String existUser(String username, String password) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	System.out.println(">> DataAccess: existUser=> username= "+username+" question= "+password);
	Query query = session.createQuery("FROM User WHERE userName=:username AND password=:password");
	query.setParameter("username", username);
	query.setParameter("password", password);
	List<User> users = query.list();	
	session.getTransaction().commit();
	if (users.size()==1) {
		return "OK";
	}
	else {
		return "error";
	}
	
}

public String createUser(String userName, String password, String name, String lastName,	String email) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	Query query = session.createQuery("FROM User WHERE userName= :username");
	query.setParameter("username", userName);
	List<User> users = query.list();	
	if (users.size()==0) {
		User newUser = new User(userName, password, name, lastName, email);
		session.persist(newUser);
		session.getTransaction().commit();
		return "CREATED";
	}
	else {
		session.getTransaction().commit();
		return "error";
	}
	
}

public String insertMoney(String username, double money) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	User user = (User) session.load(User.class, username);
	user.setMoney(user.getMoney()+money);
	session.persist(user);
	session.getTransaction().commit();
	return "INSERTED";
	
}

public String removeEvent(Event event) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	Integer eventId = event.getEventNumber();
	Event ev = (Event) session.load(Event.class, eventId);
	for (Question q:ev.getQuestions()) {
		session.delete(q);
	}
	System.out.println("Questions deleted");
	session.delete(ev);
	System.out.println("Event removed");
	session.getTransaction().commit();
	return "REMOVED";
}

public String createEvent(String description, Integer year, Integer month, Integer day) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	Event ev = new Event(description,UtilDate.newDate(year,month-1,day));
	session.persist(ev);
	session.getTransaction().commit();
	System.out.println("Event Created");
	return "CREATED";
}

}


