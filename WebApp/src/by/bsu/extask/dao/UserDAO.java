package by.bsu.extask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.bsu.extask.to.UserData;
import by.bsu.extask.to.UserList;

public class UserDAO {
	
	public static UserList userList() throws DAOException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		UserList userList = null;
	    
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	rs = st.executeQuery("SELECT id_users, name, surname, login, email FROM users WHERE status='user'");
	    	
	    	List<UserData> users = new ArrayList<UserData>();
	    	UserData data = null;
	    	while (rs.next()){
	    		data = new UserData(rs.getInt("id_users"), rs.getString("login"), rs.getString("email"), rs.getString("name"), rs.getString("surname"), "user");
	    		users.add(data);
	    	}
	    	
	    	userList = new UserList(users);
	    	
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

		return userList;	
	}
	
	public static UserData checkUserById(int id) throws DAOException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		UserData data = null;
	    
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	System.out.println("в UserDAO checkUserbyId до транзакции");
	    	rs = st.executeQuery("SELECT name, surname, status FROM users WHERE id_users="+id);
	    	System.out.println("после транзакции");
	    	if (rs.next()){
	    		data = new UserData(id, rs.getString("name"), rs.getString("surname"), rs.getString("status"));
	    	}
	    	
	    } catch (ClassNotFoundException e) {
	    	System.out.println("55");
	    	throw new DAOException("Ошибка загрузки драйвера.");
	    } catch (SQLException e) {
	    	System.out.println("checkUserById");
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
	
	public static UserData getUserIdByLogin(String login) throws DAOException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		UserData data = null;
	    
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8", "root", "root");
	    	st = con.createStatement();
	    	System.out.println("в UserDAO checkUserbyId до транзакции");
	    	rs = st.executeQuery("SELECT id_users, name, surname, status FROM users WHERE login='" + login + "'");
	    	System.out.println("после транзакции");
	    	if (rs.next()){
	    		data = new UserData(rs.getInt("id_users"), rs.getString("name"), rs.getString("surname"), rs.getString("status"));
	    	}
	    	
	    } catch (ClassNotFoundException e) {
	    	System.out.println("55");
	    	throw new DAOException("Ошибка загрузки драйвера.");
	    } catch (SQLException e) {
	    	System.out.println("checkUserById");
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
