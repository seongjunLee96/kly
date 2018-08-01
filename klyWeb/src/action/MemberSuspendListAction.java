package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.BoardBean;
import bean.MemberBean;
import service.MemberSuspendListService;

public class MemberSuspendListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 로그인 여부 확인
		// 로그인한 아이디가 admin인지 확인.
		// 로그인이 되어있고, admin일경우에만 상세보기 가능하게

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = null;
		//if (id == "admin") {
			MemberSuspendListService memberSuspendListService = new MemberSuspendListService();
			ArrayList<MemberBean> MemberSuspendList = MemberSuspendListService.getMemberSuspendList(); //boardSuspendList 객체배열선언
			forward = new ActionForward();
			request.setAttribute("MemberSuspendList", MemberSuspendList);
			System.out.println("membeSuspendAction");
			System.out.println("MemberSuspendList의 사이즈 : " + MemberSuspendList.size());
			forward.setPath("./adminMember.jsp");
		/*} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자가 아닙니다.')");
			out.println("location.href='./memberLogin.kly'</script>");
		} */
		
		return forward;

	}

}
