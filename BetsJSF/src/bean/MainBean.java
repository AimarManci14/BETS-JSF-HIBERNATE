package bean;

import java.io.Console;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class MainBean {

	private Date data;
	private Event ev;
	private Question q;
	private float betMin;
	private String year;
	private String month;
	private String day;
	private String description;
	private List<Question> questions;
	public String question;
	BLFacade facadeBL = FacadeBean.getBusinessLogic();
	List<Event> events = facadeBL.getEvents(data); 
	
	public BLFacade getFacadeBL() {
		return facadeBL;
	}
	public void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
	}
	
	public Event getEv() {
		return ev;
	}

	public void setEv(Event ev) {
		this.ev = ev;
	}

	public float getBetMin() {
		return betMin;
	}

	public void setBetMin(float betMin) {
		this.betMin = betMin;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEtxeak(List<Event> events) {
		this.events = events;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public void onDateSelect(SelectEvent event) {
		questions = null;
		data = (Date) event.getObject();
		events = facadeBL.getEvents(data);
	}
	
	public void onEventSelect(SelectEvent event) {
		ev = (Event) event.getObject();
		questions = ev.getQuestions();
	}
	public void onQuestionSelect(SelectEvent question) {
		q = (Question) question.getObject();
		System.out.println(q);
	}
	
	public void createQuestion() throws EventFinished, QuestionAlreadyExist {
		if(ev==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Have to choose an event."));
		}
		else if(question.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Have to choose a question."));
		}
		else if(betMin < 0.0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Bet cannot be negative."));
		}
		else {
			try {
				facadeBL.createQuestion(ev, question, betMin);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Question created"));
			}
			catch (EventFinished e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Event is finished"));
			}
			
			
		}
	}
	
	public void emptyData(ActionEvent e) {
		data = null;
		events = null;
		ev = null;
		question = null;
		betMin = 0;
		year = "";
		month ="";
		day="";
		description="";
	}
	
	public void updateDay(ActionEvent e) {
		data = null;
		events = null;
		questions = null;
		ev = null;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}

	public String removeEvent() {
		if(ev==null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Event not selected."));
			return "error";
		}
		else {
			return facadeBL.removeEvent(ev);
		}
	}
	
	public String createEvent() {
		if (year.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Year is empty or is past."));
			return "error";
		}
		if (month.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Month is empty or is past."));
			return "error";
		}
		if (day.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Day is empty or is past."));
			return "error";
		}
		if (description.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Description is empty."));
			return "error";
		}
		return facadeBL.createEvent(description, Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Question getQ() {
		return q;
	}
	public void setQ(Question q) {
		this.q = q;
	}
		
}
