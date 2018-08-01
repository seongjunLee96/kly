package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import service.BoardSuspendDeleteService;

public class BoardSuspendDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int delNum = Integer.parseInt(request.getParameter("BOARD_NUM"));
		System.out.println("BoardSuspendDelteAction");
		System.out.println("BOARD_NUM의 값 : " + delNum);
		
		ActionForward forward = null;
		BoardSuspendDeleteService boardSuspendDeleteService = new BoardSuspendDeleteService();
		boolean deleteResult = boardSuspendDeleteService.deleteSuspendBoard(delNum);
		if (deleteResult) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제완료.')");
			out.println("</script>");
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./boardSuspendList.kly");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제실패.')");
			out.println("location.href='./boardSuspendList.kly'</script>");
		}
		return forward;
	}

}