package by.bsu.extask.bl;

import by.bsu.extask.controller.InDataWrapper;
import by.bsu.extask.dao.DAOException;
import by.bsu.extask.dao.RequestDAO;
import by.bsu.extask.dao.UserDAO;
import by.bsu.extask.to.RequestList;
import by.bsu.extask.to.UserData;

public class HistoryPageCommand implements Command{
	private static String userIdParam = "user_id";
	
	@Override
	public String execute(InDataWrapper inData) throws DataException { 
		String userId, page=null;
		userId = (String)inData.getRequestParametr(userIdParam);
		System.out.println(userId);
		int userIdFun = Integer.parseInt(userId);
		UserData userData = null;
		try {			
			System.out.println("HistoryPageCommand до userData ");
			userData = UserDAO.checkUserById(userIdFun);
			System.out.println("HistoryPageCommand после userData ");
			if (userData == null){
				System.out.println("userData - null");
				page = "/WEB-INF/jsp/error_page.jsp";
				return page;
			}
			inData.setRequestAttribute("userData", userData);
			
			System.out.println("HistoryPageCommand перед  RequestDAO ");
			RequestList reqList;
			try {
				reqList = RequestDAO.requestList();
			} catch (DAOException e) {
				throw new DataException("Ошибка работы с источником данных.");
			}
			if (reqList == null){
				throw new DataException("Ошибка работы с источником данных. Нет пользователей.");
			}
			inData.setRequestAttribute("requestList", reqList);
		}catch (DAOException e) {
			throw new DataException("Ошибка работы с источником данных.");
		}
		page = "/WEB-INF/jsp/history_room_page.jsp";
		return page;
			
	}
}
