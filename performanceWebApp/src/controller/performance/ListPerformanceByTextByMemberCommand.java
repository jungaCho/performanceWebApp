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
import model.service.performance.PerformanceService;
public class ListPerformanceByTextByMemberCommand implements Command {

    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        //commandFactory�� SelectPerformanceByText.do �� ���κ�Ź�����

        int startRow = Integer.parseInt(req.getParameter("startRow"));
        int endRow = Integer.parseInt(req.getParameter("endRow"));
        String mode = req.getParameter("mode");

        ActionForward forward = new ActionForward();

        try {

            HashMap<String, Object> map = new HashMap<String, Object>();
            
            map.put("startRow",startRow);
            map.put("endRow",endRow);
            map.put("mode",mode);

            PerformanceService service = PerformanceService.getInstance();
            List<PerformanceVO> perfomances = service.retrievePerformanceListByMember(map);

            if(perfomances != null) {
                req.setAttribute("perfomances", perfomances);
                forward.setPath("/performanceList.jsp"); //json�� ��ü�� ���ε��Ǿ��ִ� ������
                forward.setRedirect(false);
            } else {
                //�� �׳� ���⼱ �ƹ��������� �̵�
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
