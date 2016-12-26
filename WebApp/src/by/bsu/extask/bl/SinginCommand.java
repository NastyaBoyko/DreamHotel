package by.bsu.extask.bl;

import by.bsu.extask.controller.InDataWrapper;
import by.bsu.extask.dao.DAOException;
import by.bsu.extask.dao.RoomDAO;
import by.bsu.extask.dao.SinginDAO;
import by.bsu.extask.dao.UserDAO;
import by.bsu.extask.to.RoomList;
import by.bsu.extask.to.UserData;

public class SinginCommand implements Command {
	private static String nameParam = "name";
	private static String surnameParam = "surname";
	private static String emailParam = "email";
	private static String loginParam = "login";
	private static String passwordParam = "password";
	
	@Override
	public String execute(InDataWrapper inData) throws DataException {
		String name;
		String surname;
		String email;
		String login;
		String password;
		
		name = inData.getRequestParametr(nameParam).toString();
		surname = inData.getRequestParametr(surnameParam).toString();
		email = inData.getRequestParametr(emailParam).toString();
		login = inData.getRequestParametr(loginParam).toString();
		password = inData.getRequestParametr(passwordParam).toString();

		UserData userData = null;
		String page = null;
		
		try {
			userData = SinginDAO.newUser(name, surname, email, login, password);
			userData = UserDAO.getUserIdByLogin(login);
			
			if (userData == null){
				page = "/WEB-INF/jsp/error_login_page.jsp";
				return page;
			}
			
			inData.setSessionAttribute("login", "OK");
			inData.setRequestAttribute("userData", userData);
			
			RoomList roomList;
			try {
				roomList = RoomDAO.roomList();
			} catch (DAOException e) {
				throw new DataException("Ошибка работы с источником данных.");
			}
			if (roomList == null){
				throw new DataException("Ошибка работы с источником данных. Нет пользователей.");
			}
			inData.setRequestAttribute("roomList", roomList);
			
			switch(userData.status){
			case "user":
				page = "/WEB-INF/jsp/user_main_page.jsp";
				break;
			case "admin":
				page = "/WEB-INF/jsp/admin_main_page.jsp";  
				break;
			}
		} catch (DAOException e) {
			throw new DataException("Ошибка работы с источником данных.");
		}
				
		return page;
	}
}
