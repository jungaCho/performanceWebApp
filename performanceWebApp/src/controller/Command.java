package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {   //public abstract 생략되있어
	ActionForward execute(HttpServletRequest req, HttpServletResponse resp) 
		throws IOException, ServletException;
}
