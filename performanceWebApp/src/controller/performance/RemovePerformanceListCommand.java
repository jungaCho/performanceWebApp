package controller.performance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class RemovePerformanceListCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
	
		String[] pNos = req.getParameterValues("checked");
		
		for(String temp: pNos) {
			System.out.println("temp: "+ temp.toString());
		}
		
		
		return null;
		
	}
	
}
