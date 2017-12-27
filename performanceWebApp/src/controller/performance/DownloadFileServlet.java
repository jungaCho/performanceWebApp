package controller.performance;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//파일 다운로드 요청을 처리할 서블릿 클래스 구현
public class DownloadFileServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 오리지널 파일명과 시스템 파일명을 구한다.
		String orignalFileName = req.getParameter("orignalFileName");
		String systemFileName = req.getParameter("systemFileName");
		
		String userAgent = req.getHeader("User-Agent");
		if(userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1) {
			//IE인 경우
			orignalFileName = URLEncoder.encode(orignalFileName, "utf-8"); 
		} else {
			//다른 웹 브라우저인 경우
			orignalFileName = new String(orignalFileName.getBytes("utf-8"), "ISO-8859-1");			
		}
		
		resp.setContentType("application/octet-stream");		
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + orignalFileName + "\";");
	
		
		FileInputStream fis = null;
		ServletOutputStream sos = null;
		try {
			String path = getServletContext().getRealPath("/upload");
			System.out.println("path : " + path);
			fis = new FileInputStream(path + File.separator + systemFileName);
			
			sos = resp.getOutputStream();
			
			byte[] buf = new byte[1024];
			int readByte = 0;
			while((readByte = fis.read(buf)) != -1) {
				sos.write(buf, 0, readByte);				
			}
			
		} catch (Exception e) {
			req.setAttribute("exception", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			dispatcher.forward(req, resp);
		} finally {
			try {
				if(fis != null) fis.close();
				if(sos != null) sos.close();
			} catch (Exception e2) {
				req.setAttribute("exception", e2);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
				dispatcher.forward(req, resp);
			}
		}		
	}	
}














