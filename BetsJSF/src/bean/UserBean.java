package bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import businessLogic.BLFacade;

public class UserBean {
	private String userName;
	private String password;
	private String name;
	private String lastName;
	private String email;
	private String money;
	private BLFacade facadeBL = FacadeBean.getBusinessLogic();
	
	
	public String insertMoney() {
		if(Double.parseDouble(money)<=0 || money=="") {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Inserted amount in not correct."));
			return "error";
		}
		else {
			String result = facadeBL.insertMoney(userName, Double.parseDouble(money));
			if (result.equals("error")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Problem encountered."));
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Money inserted"));
			}
			return result;
		}
	}
	public void emptyData() {
		userName = "";
		password = "";
		name = "";
		lastName = "";
		email = "";
	}
	
	public String createUser() {
		if(userName.equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: You have to choose a UserName."));
			return "error";
		}
		else if(password.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: You have to choose a password."));
				return "error";
		}
		else if(name.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: You have to insert a name.."));
			return "error";
		}
		else if(lastName.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: You have to insert a lastname."));
			return "error";
		} 
		else if(email.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: You have to insert a email."));
			return "error";
		} else {
			String result = facadeBL.createUser(userName, password, name, lastName, email);
			if (result.equals("error")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: That username already exists."));
			}
			return result;
		}
	}
	
	public String existUser() {
		if(userName.equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: You have to choose a UserName."));
			return "error";
		}
		else if(password.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: You have to choose a password."));
				return "error";
		}
		else {
			String result = facadeBL.existUser(userName, password);
			if (result.equals("error")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Not registered User."));
			}
			return result;
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
}
