package by.bsu.extask.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InDataWrapper {
	private HttpServletRequest request;
	private HttpSession session;
	
	public InDataWrapper(HttpServletRequest request){
		this.request = request;
		this.session = request.getSession(true);
	}
	
	public Object getRequestParametr(String param){
		return request.getParameter(param);
	}
	
	public void setRequestAttribute(String key, Object attr){
		request.setAttribute(key, attr);
	}
	
	public Object getRequestAttribute(String key){
		return request.getAttribute(key);
	}
	
	public void  setSessionAttribute(String key, Object attr){
		session.setAttribute(key, attr);
		
	}
	
	public Object getSessionAttribute(String key){
		return session.getAttribute(key);
	}
	
	public void sessionInvalidate() {
		session.invalidate();
	}
}
