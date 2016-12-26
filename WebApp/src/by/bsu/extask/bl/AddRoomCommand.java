package by.bsu.extask.bl;

import by.bsu.extask.controller.InDataWrapper;
import by.bsu.extask.dao.DAOException;
import by.bsu.extask.dao.RoomDAO;
import by.bsu.extask.dao.RoomTypeDAO;
import by.bsu.extask.dao.UserDAO;
import by.bsu.extask.to.RoomData;
import by.bsu.extask.to.RoomTypeData;
import by.bsu.extask.to.UserData;

public class AddRoomCommand implements Command {
	private static String numberroomParam = "room_id";
	private static String maxpersonParam = "max_person";
	private static String priceParam = "price_per_night";
	private static String roomtypeParam = "nametype";
	
	private static String userIdParam = "user_id";
	
	@Override
	public String execute(InDataWrapper inData) throws DataException {
		String numberroom, maxperson, price, roomtypeid, userId, page = null ;
		
		numberroom = (String)inData.getRequestParametr(numberroomParam);
		maxperson = (String)inData.getRequestParametr(maxpersonParam);
		price = (String)inData.getRequestParametr(priceParam);
		
		roomtypeid = inData.getRequestParametr(roomtypeParam).toString();
				
		userId = (String)inData.getRequestParametr(userIdParam);
		int roomnumberF = Integer.parseInt(numberroom);
		int maxpersonF = Integer.parseInt(maxperson);
		int roomtypeidF = Integer.parseInt(roomtypeid);
		double priceF = Double.parseDouble(price);
		
		int userIdF = Integer.parseInt(userId);
			
		RoomData newroom = null;
		RoomTypeData type = null;
		
		UserData userData = null;
		try {
			System.out.println("addroomcommand до userData");
			userData = UserDAO.checkUserById(userIdF);
			System.out.println("addroomcommand перед type");
			type = RoomTypeDAO.roomtypeData(roomtypeidF);		
			
			newroom = RoomDAO.addRoom(roomnumberF, maxpersonF, priceF, type);
			if (newroom == null){
				System.out.println("reservecommand reqData = null");
				page = "/WEB-INF/jsp/added_room_page.jsp";
				return page;
			}
			inData.setRequestAttribute("newRoom", newroom);
			inData.setRequestAttribute("userData", userData);
			page = "/WEB-INF/jsp/added_room_page.jsp";
		}catch (DAOException e) {
			throw new DataException("Ошибка работы с источником данных.");
		}
		return page;
	}
		
}
