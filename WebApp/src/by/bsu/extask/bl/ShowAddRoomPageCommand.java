package by.bsu.extask.bl;

import by.bsu.extask.controller.InDataWrapper;
import by.bsu.extask.dao.DAOException;
import by.bsu.extask.dao.RoomTypeDAO;
import by.bsu.extask.dao.UserDAO;
import by.bsu.extask.to.RoomTypeList;
import by.bsu.extask.to.UserData;

public class ShowAddRoomPageCommand implements Command {
	private static String userIdParam = "user_id";
	
	@Override
	public String execute(InDataWrapper inData) throws DataException { 
		String userId;
		userId = (String)inData.getRequestParametr(userIdParam);
		int userIdFun = Integer.parseInt(userId);
		
		UserData userD = null;
		RoomTypeList types = null;
		
		try {
			types = RoomTypeDAO.roomtypeList();
			userD = UserDAO.checkUserById(userIdFun);
			inData.setRequestAttribute("userData1", userD);
			inData.setRequestAttribute("roomTypes", types);
		} catch (DAOException e) {
			throw new DataException("Ошибка работы с источником данных.");
		}
		
		return "/WEB-INF/jsp/add_room_page.jsp";
		
	}
}
