package by.bsu.extask.bl;

import by.bsu.extask.controller.InDataWrapper;

public class ShowSinginPageCommand implements Command {
	@Override
	public String execute(InDataWrapper inData) throws DataException {
		
		return "/WEB-INF/jsp/singin_page.jsp";
	}
}
