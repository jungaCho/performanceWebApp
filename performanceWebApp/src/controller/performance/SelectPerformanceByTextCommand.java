package controller.performance;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import domain.performance.PerformanceVO;
import model.service.performance.PerformanceService;

public class SelectPerformanceByTextCommand implements Command {

    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        //commandFactory�� SelectPerformanceByText.do �� ���κ�Ź�����

        int startRow = Integer.parseInt(req.getParameter("startRow"));
        int endRow = Integer.parseInt(req.getParameter("endRow"));
        String mode = req.getParameter("mode");
        String genre = req.getParameter("genre");
        String keyword = req.getParameter("keyword");
        String month = req.getParameter("month");


        ActionForward forward = new ActionForward();

        try {

            HashMap<String, Object> map = new HashMap<String, Object>();

            //�� �������ʿ���� �̷��� �� ���ļ� �ѱ�� �ɵ�

            map.put("startRow",startRow);
            map.put("endRow",endRow);
            map.put("mode",mode);
            map.put("genre",genre);
            map.put("keyword",keyword);
            map.put("month",month);
            
            System.out.println(startRow);
            System.out.println(endRow);
            System.out.println(keyword);
            System.out.println(mode);
            
         
            PerformanceService service = PerformanceService.getInstance();
            List<PerformanceVO> perfomances = service.retrievePerformanceListByMember2(map);
            
            for(PerformanceVO perfomance : perfomances) {
				System.out.println(perfomance.toString());
			}
			

            if(perfomances != null) {
            	
                req.setAttribute("perfomances", perfomances);
                forward.setPath("/performanceList.jsp"); //json�� ��ü�� ���ε��Ǿ��ִ� ������
                forward.setRedirect(false);
                
            } else {
           
                forward.setPath("/member_p_layout2.jsp?article=member_p_selectPerformance"); //json�� ��ü�� ���ε��Ǿ��ִ� ������
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