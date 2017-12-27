package controller.performance;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.performance.DetailFileVO;
import domain.performance.PerformanceVO;
import domain.performance.PosterVO;
import model.service.performance.PerformanceService;
import util.UploadFiles;

public class ModifyPerformanceServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pNo = getStringFromStream(req.getPart("no").getInputStream());

		try {
			PerformanceService service = PerformanceService.getInstance();
			PerformanceVO performance = service.retirevePerformance(pNo);
			
			performance.getPosters().clear();
			performance.getDetailFiles().clear();
			
		

			Collection<Part> parts = req.getParts();
			String removeDetailFile = null;
			String removePoster=null;
			for (Part part : parts) {
				if (part.getContentType() == null) { // �Ϲ� �����ʹ� contentType �� null���� ������, ���ε�������� contentType�����Ǿ�����
					// �Ϲ� �������� ���
					switch (part.getName()) { // ���ε�������� �������ִ�part��ü�� getName()���� �Ӽ��̸��� �����´�.
					case "title": // �Ӽ��̸��� "" �� ��� -> ��ɼ���
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
					/*case "theater":
						
						performance.settName(getStringFromStream(part.getInputStream()));
						System.out.println(getStringFromStream(part.getInputStream()));
						break;*/
					case "viewClass":
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
					case "removeDetailFile":
						removeDetailFile=getStringFromStream(part.getInputStream());
						System.out.println(getStringFromStream(part.getInputStream()));
						break;
					case "removePoster":
						removePoster=getStringFromStream(part.getInputStream());
						System.out.println(getStringFromStream(part.getInputStream()));
						break;	
					}
				} else {
					// uploadfile�ΰ��
					if (part.getSize() > 0) {
						ServletContext sc = getServletContext();
						// ������ �ø���쿡�� (0���� ����� ū ���) ������ upload������ ���ε��Ѵ�.

						// poster �� ���
						if (part.getName().equals("poster")) {
							PosterVO poster = UploadFiles.uploadPosterFile(part, sc);
							poster.setMainPoster(0);
							performance.addPoster(poster);
						} else if (part.getName().equals("mainPoster")) {
							PosterVO poster = UploadFiles.uploadPosterFile(part, sc);
							poster.setMainPoster(1);
							performance.addPoster(poster);
						} else if (part.getName().equals("detailFile")) {
							// detailFile �� ���
							DetailFileVO detailFile = UploadFiles.uploadDetailFile(part, sc);
							performance.addDetailFile(detailFile);
						}
					}
				}
			} // end of for clause
			// update
			
			
			List<String> removePosters = new ArrayList<String>();// ������ ���� ���
			if(removeDetailFile != null) {				
				removePosters = Arrays.asList(removePoster.split(","));			
			}
			
			List<String> removeDetailFiles = new ArrayList<String>();// �󼼼��� ���� ���
			if(removeDetailFile != null) {				
				removeDetailFiles = Arrays.asList(removeDetailFile.split(","));			
			}
			
			service.modifyPerformance(performance, removePosters,removeDetailFiles);
			resp.sendRedirect(req.getContextPath() + "/admin_p_selectPerformanceList.do");

		} catch (Exception e) {
			req.setAttribute("exception", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			dispatcher.forward(req, resp);
		}
	}

	// �о���� ����(����Ʈ��������)�� �������·� ��ȯ��Ű�� �޼ҵ�
	private String getStringFromStream(InputStream is) throws IOException {

		// ����Ʈ�� �����͸� ���������� ��ȯ
		StringBuilder str = new StringBuilder();
		InputStreamReader isr = null; // ����Ʈ�������͸� �б����� �ʿ�
		try {
			isr = new InputStreamReader(is, "utf-8");
			char[] cbuf = new char[256];
			int readChar = 0;
			while ((readChar = isr.read(cbuf)) != -1) {
				str.append(new String(cbuf, 0, readChar)); // ������ ���ڿ��� Stringbuilder�� ��� �߰�
			}
		} finally {
			if (isr != null)
				isr.close();
		}

		return str.toString();
	}

}