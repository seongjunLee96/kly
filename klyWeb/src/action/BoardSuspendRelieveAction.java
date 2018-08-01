package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import service.BoardSuspendRelieveService;

public class BoardSuspendRelieveAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int relieveNum = Integer.parseInt(request.getParameter("BOARD_NUM"));
		System.out.println("BoardSuspendRelieveAction");
		System.out.println("BOARD_NUM의 값 : " + relieveNum);
		
		ActionForward forward = null;
		BoardSuspendRelieveService boardSuspendRelieveService = new BoardSuspendRelieveService();
		boolean deleteResult = boardSuspendRelieveService.RelieveSuspendBoard(relieveNum);
		if (deleteResult) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('해제완료.')");
			out.println("</script>");
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./boardSuspendList.kly");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('해제실패.')");
			out.println("location.href='./boardSuspendList.kly'</script>");
		}
		return forward;
	}
	
}