package by.bsu.extask.bl;

import by.bsu.extask.controller.InDataWrapper; 

public class LogoutCommand implements Command {
	
	@Override
	public String execute(InDataWrapper inData) throws Exception {
		inData.sessionInvalidate();
		return "/index.jsp";
	}
}
