package by.bsu.extask.bl;

import by.bsu.extask.controller.InDataWrapper;
import by.bsu.extask.dao.DAOException;
import by.bsu.extask.dao.LoginDAO;
import by.bsu.extask.dao.RoomDAO;
import by.bsu.extask.to.RoomList;
import by.bsu.extask.to.UserData;

public class LoginCommand implements Command {
	private static String loginParam = "login";
	private static String passwordParam = "password";

	@Override
	public String execute(InDataWrapper inData) throws DataException {
		
		String login;
		String password;
		
		login = inData.getRequestParametr(loginParam).toString();
		password = inData.getRequestParametr(passwordParam).toString();

		UserData userData = null;
		String page = null;
		
		try {
			userData = LoginDAO.checkUser(login, password);
			
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
