package by.bsu.extask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.bsu.extask.to.RoomData;
import by.bsu.extask.to.RoomList;
import by.bsu.extask.to.RoomTypeData;

public class RoomDAO {
	public static RoomList roomList() throws DAOException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RoomList roomList = null;
	    
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	rs = st.executeQuery("SELECT id_room, maxperson, pricepernight, nameroomtype FROM room, roomtype WHERE room.roomtype_id_roomtype=roomtype.id_roomtype");
	    	List<RoomData> rooms = new ArrayList<RoomData>();
	    	RoomData data = null;
	    	while (rs.next()){
	    		data = new RoomData(rs.getInt("id_room"), rs.getInt("maxperson"), rs.getDouble("pricepernight"), rs.getString("nameroomtype"));
	    		rooms.add(data); 
	    	}
	    	
	    	roomList = new RoomList(rooms);
	    	
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
		return roomList;
	}
	
	public static RoomData getRoomById(int id) throws DAOException{ 
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RoomData data = null;
	    
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	rs = st.executeQuery("SELECT id_room, maxperson, pricepernight, nameroomtype FROM room, roomtype WHERE room.roomtype_id_roomtype=roomtype.id_roomtype AND room.id_room="+ id);

	    	if (rs.next()){
	    		data = new RoomData(id, rs.getInt("maxperson"), rs.getDouble("pricepernight"), rs.getString("nameroomtype"));
	    	}
	    	
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
	
	public static RoomData addRoom(int id, int max_per, double price, RoomTypeData roomtype ) throws DAOException{ 
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RoomData data = null;
	    
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	int w = st.executeUpdate("INSERT INTO room (id_room, maxperson, pricepernight, roomtype_id_roomtype) VALUES (" 
	    			+ id + "," 
	    			+ max_per + ","
	    			+ price + ","
	    			+ roomtype.id_roomtype + ")");
	    	    	
	    	    	/*ПРОВЕРКА НА ДОБАВЛЕНИЕ*/
	    	    	if (w == 0) {
	    	    		System.out.println("not now insert");
	    	    	}
	    	    	else { 
	    	    		System.out.println("yiiiiiii insert");
	    	    		data = new RoomData(id, max_per, price, roomtype.roomtype);
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
	
	
	public static RoomList getRoomByMaxPerson(int max_per) throws DAOException{ 
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RoomList roomList = null;
	    
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	rs = st.executeQuery("SELECT id_room FROM room WHERE room.maxperson>"+ (max_per-1));
	    	List<RoomData> rooms = new ArrayList<RoomData>();
	    	RoomData data = null;
	    	while (rs.next()){
	    		data = getRoomById(rs.getInt("id_room"));
	    		System.out.println(data.number_room);
	    		rooms.add(data); 
	    	}
	    	
	    	roomList = new RoomList(rooms);
	    	
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
		return roomList;
	}
	
	
}
