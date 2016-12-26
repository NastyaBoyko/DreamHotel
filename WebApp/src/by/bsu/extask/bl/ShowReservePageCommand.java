package by.bsu.extask.bl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import by.bsu.extask.controller.InDataWrapper;
import by.bsu.extask.dao.DAOException;
import by.bsu.extask.dao.NumberRoomDAO;
import by.bsu.extask.dao.UserDAO;
import by.bsu.extask.to.RoomData;
import by.bsu.extask.to.UserData;

public class ShowReservePageCommand implements Command {
	private static String dateinParam = "date_in";
	private static String dateoutParam = "date_out";
	private static String roomParam = "number_room";
	private static String userIdParam = "user_id";
	
	@Override
	public String execute(InDataWrapper inData) throws DataException {
		String roomNum, userId, dateIn, dateOut;
		roomNum = (String)inData.getRequestParametr(roomParam);
		userId = (String)inData.getRequestParametr(userIdParam);
		
		int roomFun = Integer.parseInt(roomNum);
		int userIdFun = Integer.parseInt(userId);
		
		/*DATE*/
		dateIn = (String)inData.getRequestParametr(dateinParam);
		dateOut = (String)inData.getRequestParametr(dateoutParam);
		Date datein = null;
		Date dateout = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDatein = format.parse(dateIn);
			java.util.Date utilDateout = format.parse(dateOut);
			datein = new java.sql.Date(utilDatein.getTime());
			dateout = new java.sql.Date(utilDateout.getTime());
		} catch (ParseException e) {
			System.out.println ("не тот тип данных DATE :(");
		}
		/*DATE*/
		
		UserData userD = null;
		RoomData roomData = null;
		String page = null;
		
		try {
			roomData = NumberRoomDAO.roomObj(roomFun);
			System.out.println("в ShowReservePageCommand до userDAO");
			userD = UserDAO.checkUserById(userIdFun);

			
			if (roomData == null){
				page = "/WEB-INF/jsp/error_page.jsp";
				return page;
			}
			inData.setRequestAttribute("roomData1", roomData);
			inData.setRequestAttribute("userData1", userD);
			inData.setRequestAttribute("dateIn", datein);
			inData.setRequestAttribute("dateOut", dateout);
		} 
		catch (DAOException e) {
			throw new DataException("Ошибка работы с источником данных.");
		}
		
		return "/WEB-INF/jsp/reserve_page.jsp";
	}
		
}
