package by.bsu.extask.bl;

import by.bsu.extask.controller.InDataWrapper;
import by.bsu.extask.dao.DAOException;
import by.bsu.extask.dao.RoomDAO;
import by.bsu.extask.dao.UserDAO;
import by.bsu.extask.to.RoomList;
import by.bsu.extask.to.UserData;

public class ToHomePageCommand implements Command {
	private static String userIdParam = "id_user";
	@Override
	public String execute(InDataWrapper inData) throws DataException { 
		String userId, page=null;
		userId = (String)inData.getRequestParametr(userIdParam);
		System.out.println(userId);
		int userIdFun = Integer.parseInt(userId);
		UserData userData = null;
		
		try {			
			System.out.println("�� userData ");
			userData = UserDAO.checkUserById(userIdFun);
			System.out.println("����� userData ");
			if (userData == null){
				System.out.println("userData - null");
				page = "/WEB-INF/jsp/error_page.jsp";
				return page;
			}
			System.out.println("��������� �������� userData ");
			inData.setRequestAttribute("userData", userData);
			
			RoomList roomList;
			try {
				roomList = RoomDAO.roomList();
			} catch (DAOException e) {
				throw new DataException("������ ������ � ���������� ������.");
			}
			if (roomList == null){
				throw new DataException("������ ������ � ���������� ������. ��� �������������.");
			}
			inData.setRequestAttribute("roomList", roomList);
			
			
			System.out.println("����� switch ");
			switch(userData.status){
			case "user":
				page = "/WEB-INF/jsp/user_main_page.jsp";
				break;
			case "admin":
				page = "/WEB-INF/jsp/admin_main_page.jsp";  
				break;
			}
		} catch (DAOException e) {
			throw new DataException("������ ������ � ���������� ������.");
		}
		return page;
	}
}
