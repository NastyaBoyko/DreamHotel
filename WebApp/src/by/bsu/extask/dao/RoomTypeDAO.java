package by.bsu.extask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.bsu.extask.to.RoomTypeData;
import by.bsu.extask.to.RoomTypeList;

public class RoomTypeDAO {
	public static RoomTypeList roomtypeList() throws DAOException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RoomTypeList roomtypeList = null;
	    
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	rs = st.executeQuery("SELECT id_roomtype, nameroomtype FROM roomtype");
	    	
	    	List<RoomTypeData> types = new ArrayList<RoomTypeData>();
	    	RoomTypeData data = null;
	    	while (rs.next()){
	    		data = new RoomTypeData(rs.getInt("id_roomtype"), rs.getString("nameroomtype"));
	    		types.add(data);
	    	}
	    	
	    	roomtypeList = new RoomTypeList(types);
	    	
	    	
	    } catch (ClassNotFoundException e) {
	    	System.out.println("55");
	    	throw new DAOException("Ошибка загрузки драйвера.");
	    } catch (SQLException e) {
	    	System.out.println("в RoomTypeDAO");
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
		return roomtypeList;	
	}

	public static RoomTypeData roomtypeData(int roomType) throws DAOException{ 
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		RoomTypeData roomtypeData = null;
	    
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	System.out.println("RoomTypeDAO перед Query");
	    	System.out.println(roomType);
	    	rs = st.executeQuery("SELECT id_roomtype, nameroomtype FROM roomtype WHERE id_roomtype=" + roomType);
	    	System.out.println("прокатило");
	    	while (rs.next()){
	    		roomtypeData = new RoomTypeData(rs.getInt("id_roomtype"), rs.getString("nameroomtype"));
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
		return roomtypeData;	
	}
}
