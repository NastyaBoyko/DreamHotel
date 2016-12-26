package by.bsu.extask.bl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import by.bsu.extask.controller.InDataWrapper;
import by.bsu.extask.dao.DAOException;
import by.bsu.extask.dao.RequestDAO;
import by.bsu.extask.dao.RoomDAO;
import by.bsu.extask.dao.UserDAO;
import by.bsu.extask.to.RoomData;
import by.bsu.extask.to.RoomList;
import by.bsu.extask.to.UserData;

public class FindRoomPageCommand implements Command {
	private static String userIdParam = "user_id";
	private static String dateinParam = "dateIn";
	private static String dateoutParam = "dateOut";
	private static String maxpersonParam = "max_person";
	
	@Override	
	public String execute(InDataWrapper inData) throws DataException {
		String page = null;		
		RoomList roomsByMaxperson = null, badRooms = null, finalRooms=null;
		/*USER*/
		String userId;
		UserData userData = null;
		userId = (String)inData.getRequestParametr(userIdParam);
		int userIdFun = Integer.parseInt(userId);	
		/*USER*/
		String maxPer;
		maxPer = (String)inData.getRequestParametr(maxpersonParam);
		int maxperF = Integer.parseInt(maxPer);
		
		/*DATE*/
		Date datein = null;
		Date dateout = null;
		String b, c;	
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
		/*DATE*/
		
		try { 
			//roomsByMaxperson = RoomDAO.roomList();
			roomsByMaxperson = RoomDAO.getRoomByMaxPerson(maxperF);
			badRooms = RequestDAO.badRoomListByDate(datein, dateout);
			userData = UserDAO.checkUserById(userIdFun);
			
			
			
		int buf =0;
			List<RoomData> rooms = new ArrayList<RoomData>();
			for(RoomData room1 : roomsByMaxperson.roomList){
				buf = 0;
				System.out.println(room1.number_room);
				for(RoomData room2 : badRooms.roomList) {
					System.out.println("   "+ room2.number_room);
					if(room1.number_room == room2.number_room){
						buf ++;
						break;
					}
				}			
				if(buf == 0) {
					rooms.add(room1);	
					System.out.println(room1.number_room);
				}
			}
			finalRooms = new RoomList(rooms);
			inData.setRequestAttribute("inDate", datein);
			inData.setRequestAttribute("outDate", dateout);
			inData.setRequestAttribute("userData", userData);
			inData.setRequestAttribute("findRooms", finalRooms);
			page = "/WEB-INF/jsp/find_room_page.jsp";
		} catch (DAOException e) {
			throw new DataException("Ошибка работы с источником данных.");
		}
		return page;
	}
		
}
