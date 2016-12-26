package by.bsu.extask.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.bsu.extask.to.RequestData;
import by.bsu.extask.to.RequestList;
import by.bsu.extask.to.RoomData;
import by.bsu.extask.to.RoomList;
import by.bsu.extask.to.UserData;

public class RequestDAO {
	public static RequestData newRequest(int id_user, UserData user, RoomData room, Date date_in, Date date_out, double tot_price) throws DAOException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RequestData data = null;
	    
		try {			
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	System.out.println("in requestDAO");
	    	int w = st.executeUpdate("INSERT INTO request (room_id_room, users_id_users, datein, dateout, price) VALUES (" 
	    	+ room.number_room + "," + id_user + ",'" + date_in + "','" + date_out + "'," + tot_price +")");
	    	
	    	    	
	    	/*ПРОВЕРКА НА ДОБАВЛЕНИЕ*/
	    	if (w == 0) {
	    		System.out.println("not now insert");
	    	}
	    	else { 
	    	    System.out.println("yiiiiiii insert"); 
	    	    data = new RequestData(user, room, date_in, date_out, tot_price);
	    	 }
	    	 /*ПРОВЕРКА НА ДОБАВЛЕНИЕ*/
	    	
	    	
	    } catch (ClassNotFoundException e) {
	    	System.out.println("55");
	    	throw new DAOException("Ошибка загрузки драйвера.");
	    } catch (SQLException e) {
	    	System.out.println("66");
	    	throw new DAOException("Ошибка работы с источником данных.");
	    } finally {
	    	try {
	    		if (rs != null) rs.close();
	    		if (st != null) st.close();
	    		if (con != null) con.close();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    } 

		return data;
	}

	public static RequestList requestList() throws DAOException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RequestList reqList = null;
		UserData user = null;
		RoomData room = null;
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	System.out.println("RequestDAO перед Query");
	    	rs = st.executeQuery("SELECT room_id_room, users_id_users, datein, dateout, price FROM request");	    	
	    	
	    	List<RequestData> requests = new ArrayList<RequestData>();
	    	RequestData data = null;
	    	while (rs.next()){
	    		System.out.println(rs.getInt("users_id_users") + " " + rs.getInt("room_id_room"));
	    		user = UserDAO.checkUserById(rs.getInt("users_id_users"));
	    		System.out.println(user.name);
	    		room = RoomDAO.getRoomById(rs.getInt("room_id_room"));
	    		System.out.println(room.max_person);

	    		data = new RequestData(user, room, rs.getDate("datein"), rs.getDate("dateout"), rs.getDouble("price"));
	    		requests.add(data); 
	    	}	    	
	    	reqList = new RequestList(requests);
	    	
	    } catch (ClassNotFoundException e) {
	    	System.out.println("55");
	    	throw new DAOException("Ошибка загрузки драйвера.");
	    } catch (SQLException e) {
	    	System.out.println("66 RequestDAO requestList()");
	    	throw new DAOException("Ошибка работы с источником данных.");
	    } finally {
	    	try {
	    		if (rs != null) rs.close();
	    		if (st != null) st.close();
	    		if (con != null) con.close();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    } 
		return reqList;
	}

	public static RequestList requestListByUserId(int id) throws DAOException{ 
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RequestList reqList = null;
		UserData user = null;
		RoomData room = null;
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	System.out.println("RequestDAO перед Query");
	    	rs = st.executeQuery("SELECT room_id_room, users_id_users, datein, dateout, price FROM request WHERE users_id_users =" + id);	    	
	    	
	    	List<RequestData> requests = new ArrayList<RequestData>();
	    	RequestData data = null;
	    	while (rs.next()){
	    		System.out.println(rs.getInt("users_id_users") + " " + rs.getInt("room_id_room"));
	    		user = UserDAO.checkUserById(rs.getInt("users_id_users"));
	    		System.out.println(user.name);
	    		room = RoomDAO.getRoomById(rs.getInt("room_id_room"));
	    		System.out.println(room.max_person);

	    		data = new RequestData(user, room, rs.getDate("datein"), rs.getDate("dateout"), rs.getDouble("price"));
	    		requests.add(data); 
	    	}	    	
	    	reqList = new RequestList(requests);
	    	
	    } catch (ClassNotFoundException e) {
	    	System.out.println("55");
	    	throw new DAOException("Ошибка загрузки драйвера.");
	    } catch (SQLException e) {
	    	System.out.println("66 RequestDAO requestList()");
	    	throw new DAOException("Ошибка работы с источником данных.");
	    } finally {
	    	try {
	    		if (rs != null) rs.close();
	    		if (st != null) st.close();
	    		if (con != null) con.close();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    } 
		return reqList;
	}

	public static RoomList badRoomListByDate(Date in, Date out) throws DAOException{ 
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RoomData room = null;
		RoomList badrooms = null;
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	System.out.println("RequestDAO перед Query");
	    	rs = st.executeQuery("select room_id_room from request where (('" + in + "' between datein and dateout OR  '" 
	    				+ out + "' between datein and dateout) OR ( datein > '" + in + "' AND dateout < '" + out + "'))");	    	
	    	
	    	List <RoomData> badroomsL = new ArrayList<RoomData>();
	    	while (rs.next()){
	    		room = RoomDAO.getRoomById(rs.getInt("room_id_room"));
	    		System.out.println(room.max_person);
	    		badroomsL.add(room);
	    	}	    	
	    	badrooms = new RoomList(badroomsL);
	    	
	    } catch (ClassNotFoundException e) {
	    	System.out.println("55");
	    	throw new DAOException("Ошибка загрузки драйвера.");
	    } catch (SQLException e) {
	    	System.out.println("66 RequestDAO requestList()");
	    	throw new DAOException("Ошибка работы с источником данных.");
	    } finally {
	    	try {
	    		if (rs != null) rs.close();
	    		if (st != null) st.close();
	    		if (con != null) con.close();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    } 
		return badrooms;
	}
}
