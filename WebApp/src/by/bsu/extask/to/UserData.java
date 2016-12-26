package by.bsu.extask.to;

public class UserData extends ActionData{
	public int id;
	public String login;
	public String email;
	public String name;
	public String surname;
	public String status;
	
	public UserData(String name, String surname,String status){
		this.name = name;
		this.surname = surname;
		this.status = status;
	}

	public UserData(int id, String name, String surname, String status) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.status = status;
	}

	public UserData(int id, String login, String email, String name,
			String surname, String status) {
		this.id = id;
		this.login = login;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.status = status;
	}
	
	
	
	
}
