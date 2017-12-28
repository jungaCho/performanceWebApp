package controller.performance;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import domain.pub.PagingVO;
import model.dao.performance.PerformanceDAO;
import model.service.performance.PerformanceService;

public class ListPerformanceByMemberCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		   //commandFactory에 SelectPerformanceByText.do 로 매핑부탁드려여

    /*    int startRow = Integer.parseInt(req.getParameter("startRow"));
        int endRow = Integer.parseInt(req.getParameter("endRow"));
        String mode = req.getParameter("mod");
        String genre = req.getParameter("genre");
        String keyword = req.getParameter("keyword");
        String month = req.getParameter("month");*/
		
		String mode="image";

        ActionForward forward = new ActionForward();

        try {

            HashMap<String, Object> map = new HashMap<String, Object>();



            //걍 따로할필요없이 이렇게 다 합쳐서 넘기면 될듯

           /* map.put("startRow",startRow);
            map.put("endRow",endRow);*/
            map.put("mode",mode);
           /* map.put("genre",genre);
            map.put("keyword",keyword);
            map.put("month",month);*/

            PerformanceService service = PerformanceService.getInstance();
            List<PerformanceVO> perfomances = service.retrievePerformanceListByMember(map);

            if(perfomances != null) {
                req.setAttribute("perfomances", perfomances);
                forward.setPath("/performanceList.jsp"); //json에 객체들 바인딩되어있는 페이지
                forward.setRedirect(false);
            } else {
            	forward.setPath("/member_p_layout2.jsp/article=member_m_findId");
    			forward.setRedirect(false);
    			
            }

            return forward;

        } catch (Exception e) {
            req.setAttribute("exception", e);
            forward.setPath("/error.jsp");
            forward.setRedirect(false);
            return forward;
        }
    }

}
