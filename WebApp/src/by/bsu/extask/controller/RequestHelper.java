package by.bsu.extask.controller;

import java.util.HashMap;

import by.bsu.extask.bl.AddRoomCommand;
import by.bsu.extask.bl.Command;
import by.bsu.extask.bl.FindRoomPageCommand;
import by.bsu.extask.bl.HistoryPageCommand;
import by.bsu.extask.bl.LoginCommand;
import by.bsu.extask.bl.LogoutCommand;
import by.bsu.extask.bl.ReserveCommand;
import by.bsu.extask.bl.ShowAddRoomPageCommand;
import by.bsu.extask.bl.ShowReservePageCommand;
import by.bsu.extask.bl.ShowRoomListCommand;
import by.bsu.extask.bl.ShowSinginPageCommand;
import by.bsu.extask.bl.ShowUserHistoryPageCommand;
import by.bsu.extask.bl.ShowUserListCommand;
import by.bsu.extask.bl.SinginCommand;
import by.bsu.extask.bl.ToHomePageCommand;

public class RequestHelper {
	private static RequestHelper instance = null;
	
	private HashMap<String, Command> commands;
	
	private RequestHelper(){
		commands = new HashMap<String, Command>();
		commands.put("login", new LoginCommand());
		commands.put("singin", new SinginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("show_users", new ShowUserListCommand());
		commands.put("show_singin", new ShowSinginPageCommand());
		commands.put("show_rooms", new ShowRoomListCommand());
		commands.put("reserve_page", new ShowReservePageCommand());
		commands.put("reserve", new ReserveCommand());
		commands.put("to_home_page", new ToHomePageCommand());
		commands.put("add_room_page", new ShowAddRoomPageCommand());
		commands.put("add_room", new AddRoomCommand());
		commands.put("history_room_page", new HistoryPageCommand());
		commands.put("user_history_page", new ShowUserHistoryPageCommand());
		commands.put("find_room_page", new FindRoomPageCommand());
		
	}
	
	public Command getCommand(String action){
		Command command = commands.get(action);
		return command;
	}
	
	public static RequestHelper getInstance(){
		if (instance == null){
			instance = new RequestHelper();
		}
		return instance;
	}
}
