package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet{
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String commandURI = requestURI.substring(contextPath.length()); // /writeArticleForm
		//contextPath ������ uri ���ϱ�
		
		System.out.println("commandURI : "+commandURI);
		
		//commandFactory �ν��Ͻ�����
		CommandFactory factory = CommandFactory.getInstance();
		Command command = factory.createCommand(commandURI);//********���ϴ�uri�� ���� command����, 
		//���� ������ command�� uri�� contextPath�� ������ commandURI
		
		ActionForward forward = command.execute(req, resp);
		
		if(forward.isRedirect()) {
			resp.sendRedirect(req.getContextPath() + forward.getPath());
		}else {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
			dispatcher.forward(req, resp);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}
	
}
