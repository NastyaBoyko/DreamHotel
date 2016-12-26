package by.bsu.extask.bl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import by.bsu.extask.controller.InDataWrapper;
import by.bsu.extask.dao.DAOException;
import by.bsu.extask.dao.RequestDAO;
import by.bsu.extask.dao.RoomDAO;
import by.bsu.extask.dao.UserDAO;
import by.bsu.extask.to.RequestData;
import by.bsu.extask.to.RoomData;
import by.bsu.extask.to.UserData;

public class ReserveCommand implements Command {
	private static String userIdParam = "user_id";
	private static String roomParam = "room_id";
	private static String dateinParam = "dateIn";
	private static String dateoutParam = "dateOut";
	@Override
	public String execute(InDataWrapper inData) throws DataException { 
		Date datein = null;
		Date dateout = null;
		
		String b, c;
		
		UserData userData = null;
		RoomData room = null;
		RequestData reqData = null;
		String page = null;
		
		String roomNum, userId;
		roomNum = (String)inData.getRequestParametr(roomParam);
		userId = (String)inData.getRequestParametr(userIdParam);
		int roomFun = Integer.parseInt(roomNum);
		int userIdFun = Integer.parseInt(userId);	
		
		b = inData.getRequestParametr(dateinParam).toString();
		c = inData.getRequestParametr(dateoutParam).toString();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDatein = format.parse(b);
			java.util.Date utilDateout = format.parse(c);
			datein = new java.sql.Date(utilDatein.getTime());
			dateout = new java.sql.Date(utilDateout.getTime());
		} catch (ParseException e) {
			System.out.println ("не тот тип данных DATE :(");
		}
		
		System.out.println(datein.toString());
		double tot_price = 0;
		
		long a = (dateout.getTime()- datein.getTime())/1000; //в секундах
		int days = (int) a/86400;
		
		System.out.println(days);
		try {
			
			userData = UserDAO.checkUserById(userIdFun);
			room = RoomDAO.getRoomById(roomFun);	
			tot_price = days* room.pricepernight;
			System.out.println(tot_price);
			
			reqData = RequestDAO.newRequest(userData.id, userData, room, datein, dateout, tot_price);
			System.out.println("requestDAO");
			
			if (reqData == null){
				System.out.println("reservecommand reqData = null");
				page = "/WEB-INF/jsp/error_page.jsp";
				return page;
			}
			System.out.println("requestDAO прокатил");
			inData.setRequestAttribute("reqData", reqData);
			page = "/WEB-INF/jsp/reserved_room_page.jsp";
			
		}
		catch (DAOException e) {
			throw new DataException("Ошибка работы с источником данных.");
		}
		return page;
	}
}
