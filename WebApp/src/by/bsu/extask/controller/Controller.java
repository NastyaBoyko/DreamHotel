package by.bsu.extask.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import by.bsu.extask.bl.Command;

@WebServlet("/mmm")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RequestHelper requestHelper = RequestHelper.getInstance();
       
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("command");
		Command command = requestHelper.getCommand(action);
		InDataWrapper inData = new InDataWrapper(request);
		String page;
		try {
			page = command.execute(inData);		
			
		} catch (Exception e) {
			page = "/WEB-INF/jsp/error_page.jsp";
		}		
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(page);
		dispatch.forward(request, response);
	}

}
