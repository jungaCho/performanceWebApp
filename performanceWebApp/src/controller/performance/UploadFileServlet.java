package controller.performance;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.performance.DetailFileVO;
import domain.performance.PerformanceVO;
import domain.performance.PosterVO;
import model.service.performance.PerformanceService;
import util.UploadFiles;


//파일 업로드 요청을 처리할 서블릿 클래스
public class UploadFileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PerformanceVO performance = new PerformanceVO();
		Collection<Part> parts = req.getParts();
		// 전송된 폼데이터들 (part)들을 구한다. -- 이름, 제목, 내용,비번, 첨부한 파일 다 part.
		
		for (Part part : parts) {
			if (part.getContentType() == null) { // 일반 데이터는 contentType 을 null값을 가지고, 업로드된파일은 contentType설정되어있음
				// 일반 데이터인 경우
				switch (part.getName()) { // 업로드된정보를 가지고있는part객체의 getName()으로 속성이름을 가져온다.
				case "title": // 속성이름이 "" 인 경우 -> 명령수행
					performance.setTitle(getStringFromStream(part.getInputStream())); 
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
				case "video":
					performance.setVideo(getStringFromStream(part.getInputStream()));
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
					
				case "startDate":
					performance.setStartDate(getStringFromStream(part.getInputStream())); 
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
				case "endDate":
					performance.setEndDate(getStringFromStream(part.getInputStream()));
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
				case "production":
					performance.setProduction(getStringFromStream(part.getInputStream()));
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
				case "price":
					performance.setPrice(Integer.parseInt(getStringFromStream(part.getInputStream())));
					System.out.println(getStringFromStream(part.getInputStream()));
					break;	
				case "theater":
					performance.settName(getStringFromStream(part.getInputStream()));
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
				case "viewNo":
					performance.setViewNo(getStringFromStream(part.getInputStream()));
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
				case "genre":
					performance.setGenreNo(getStringFromStream(part.getInputStream()));
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
				case "contactName":
					performance.setContactName(getStringFromStream(part.getInputStream()));
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
				case "contactNumber":
					performance.setContactNumber(getStringFromStream(part.getInputStream()));
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
				case "note":
					performance.setNote(getStringFromStream(part.getInputStream()));
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
				case "runningTime":
					performance.setRunningTime(Integer.parseInt(getStringFromStream(part.getInputStream())));
					System.out.println(getStringFromStream(part.getInputStream()));
					break;
				}
			} else {
				// uploadfile인경우
				if (part.getSize() > 0) {
					ServletContext sc=getServletContext();
					// 파일을 올린경우에만 (0보다 사이즈가 큰 경우) 파일을 upload폴더에 업로드한다.
					
					//poster 인 경우
					if(part.getName().equals("poster")) {
						PosterVO poster = UploadFiles.uploadPosterFile(part,sc);
						poster.setMainPoster(0); 
						performance.addPoster(poster);
					}else if(part.getName().equals("mainPoster")) {
						PosterVO poster = UploadFiles.uploadPosterFile(part,sc);
						poster.setMainPoster(1); 
						performance.addPoster(poster);
					}else if(part.getName().equals("detailFile")) {
						//detailFile 인 경우
						DetailFileVO detailFile = UploadFiles.uploadDetailFile(part,sc);
						performance.addDetailFile(detailFile); 
					}
				}
			}
		} // end of for clause

		try {
			// DB에 게시글을 등록
			PerformanceService performanceService = PerformanceService.getInstance();	
			performanceService.createPerformance(performance); 
			
			// 게시글 목록 조회 페이지로 이동
			resp.sendRedirect(req.getContextPath() + "/admin_p_selectPerformanceList.do");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("exception", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			dispatcher.forward(req, resp);
		}
	}

	// 읽어들인 파일(바이트형데이터)을 문자형태로 반환시키는 메소드
	private String getStringFromStream(InputStream is) throws IOException {

		// 바이트형 데이터를 문자형으로 반환
		StringBuilder str = new StringBuilder();
		InputStreamReader isr = null; // 바이트형데이터를 읽기위해 필요
		try {
			isr = new InputStreamReader(is, "utf-8");
			char[] cbuf = new char[256];
			int readChar = 0;
			while ((readChar = isr.read(cbuf)) != -1) {
				str.append(new String(cbuf, 0, readChar)); // 가져온 문자열을 Stringbuilder에 계속 추가
			}
		} finally {
			if (isr != null)
				isr.close();
		}
	
		return str.toString();
	}
	
	
}