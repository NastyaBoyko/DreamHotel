package by.bsu.extask.bl;

import by.bsu.extask.controller.InDataWrapper;
import by.bsu.extask.dao.DAOException;
import by.bsu.extask.dao.RoomDAO;
import by.bsu.extask.to.RoomList;

public class ShowRoomListCommand implements Command{
	@Override
	public String execute(InDataWrapper inData) throws DataException {
		
		RoomList roomList;
		try {
			roomList = RoomDAO.roomList();
		} catch (DAOException e) {
			throw new DataException("Ошибка работы с источником данных.");
		}
		
		if (roomList == null){
			throw new DataException("Ошибка работы с источником данных. Нет номеров.");
		}
		
		inData.setRequestAttribute("roomList", roomList);
		
		return "/WEB-INF/jsp/all_rooms_show_page.jsp";
	}
}
