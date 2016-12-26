package by.bsu.extask.bl;

import by.bsu.extask.controller.InDataWrapper;
import by.bsu.extask.dao.DAOException;
import by.bsu.extask.dao.UserDAO;
import by.bsu.extask.to.UserData;
import by.bsu.extask.to.UserList;

public class ShowUserListCommand implements Command {
	private static String userIdParam = "user_id";
	
	@Override
	public String execute(InDataWrapper inData) throws DataException {
				
		String userId;
		userId = (String)inData.getRequestParametr(userIdParam);
		int userIdFun = Integer.parseInt(userId);
		UserData userD = null;
		
		String login = (String)inData.getSessionAttribute("login");
		
		if(!"OK".equals(login)){
			return "/index.jsp";
		}
		
		UserList userList;
		try {
			userD = UserDAO.checkUserById(userIdFun);
			userList = UserDAO.userList();
		} catch (DAOException e) {
			throw new DataException("Ошибка работы с источником данных.");
		}
		
		if (userList == null){
			throw new DataException("Ошибка работы с источником данных. Нет пользователей.");
		}
		inData.setRequestAttribute("userData1", userD);
		inData.setRequestAttribute("userList", userList);
		
		return "/WEB-INF/jsp/all_users_show_page.jsp";
	}
}
