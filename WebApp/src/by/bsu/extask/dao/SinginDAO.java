package by.bsu.extask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.bsu.extask.to.UserData;

public class SinginDAO {
	public static UserData newUser(String name, String surname, String email, String login, String password) throws DAOException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		UserData data = null;
		String loginUser = login;
	    
		try {
			String nameUser = name,
					surnameUser = surname,
					emailUser = email,
					passwordUser = password,
					stat = "user";
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	int w = st.executeUpdate("INSERT INTO users (name, surname, email, login, password, status) VALUES ('" + nameUser + "','" + surnameUser + "','" + emailUser + "','" + 
	    	loginUser + "','" + passwordUser + "', 'user')");
	    	
	    	/*ПРОВЕРКА НА ДОБАВЛЕНИЕ*/
	    	if (w == 0) {
	    		System.out.println("not now insert");
	    	}
	    	else { 
	    		System.out.println("yiiiiiii insert");
	    		data = new UserData(nameUser, surnameUser, stat);
	    		
	    	}
	    	/*ПРОВЕРКА НА ДОБАВЛЕНИЕ*/
	    	
	    	
	    } catch (ClassNotFoundException e) {
	    	System.out.println("55");
	    	throw new DAOException("Ошибка загрузки драйвера.");
	    } catch (SQLException e) {
	    	System.out.println("66" + e);
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
