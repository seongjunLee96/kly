package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import service.BoardSuspendRelieveService;
import service.MemberSuspendRelieveService;

public class MemberSuspedRelieveAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String MEM_ID = request.getParameter("MEMBER_ID");
		System.out.println("MemberSuspedRelieveAction");
		System.out.println("MEMBER_ID의 값 : " + MEM_ID);
		
		ActionForward forward = null;
		MemberSuspendRelieveService memberSuspendRelieveService = new MemberSuspendRelieveService();
		boolean deleteResult = memberSuspendRelieveService.RelieveSuspendBoard(MEM_ID);
		if (deleteResult) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('해제완료.')");
			out.println("</script>");
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./memberSuspendList.kly");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('해제실패.')");
			out.println("location.href='./memberSuspendList.kly'</script>");
		}
		return forward;
	}

}
