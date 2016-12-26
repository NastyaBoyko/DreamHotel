package by.bsu.extask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.bsu.extask.to.RoomData;

public class NumberRoomDAO {
	public static RoomData roomObj(int roomNum) throws DAOException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RoomData data = null;
		//RoomList roomList = null;
	    
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	rs = st.executeQuery("SELECT maxperson, pricepernight, nameroomtype FROM room, roomtype WHERE room.roomtype_id_roomtype=roomtype.id_roomtype AND room.id_room="+roomNum);
	    	//List<RoomData> rooms = new ArrayList<RoomData>();
	    	
	    	while (rs.next()){
	    		data = new RoomData(roomNum, rs.getInt("maxperson"), rs.getDouble("pricepernight"), rs.getString("nameroomtype"));
	    		//rooms.add(data); 
	    	}
	    	
	    	//roomList = new RoomList(rooms);
	    	
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
}
