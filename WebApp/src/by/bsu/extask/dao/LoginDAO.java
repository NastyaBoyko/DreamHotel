package by.bsu.extask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.bsu.extask.to.UserData;

public class LoginDAO{
	
	public static UserData checkUser(String login, String password) throws DAOException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		UserData data = null;
	    
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	rs = st.executeQuery("SELECT id_users, name, surname, status FROM users WHERE login='"+login+"'AND password='"+password+"'");

	    	if (rs.next()){
	    		data = new UserData(rs.getInt("id_users"), rs.getString("name"), rs.getString("surname"), rs.getString("status"));
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

}
